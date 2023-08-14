-- 令牌桶算法分布式版本,借助 Redis + Lua 来实现的

local token = KEYS[1]                     -- 获取传入的参数中的令牌标识。
local now = tonumber(ARGV[1])             -- 获取当前时间戳，并将其转换为数值。

local rate = tonumber(ARGV[2])            -- 获取令牌生成速率，即每秒生成的令牌数量
local capacity = tonumber(ARGV[3])

local lastTime = tonumber(redis.call('HGET', token, 'last_time') or 0)        -- 从Redis中获取存储在哈希表中的上一次操作时间，如果不存在则默认为0。
local tokensLeft = tonumber(redis.call('HGET', token, 'tokens_left') or 0)    -- 从Redis中获取存储在哈希表中的剩余令牌数量，如果不存在则默认为0。

tokensLeft = math.min(capacity, tokensLeft + (now - lastTime) * rate)  -- 根据当前时间、上次操作时间、令牌生成速率和令牌桶容量计算出新的剩余令牌数量。这个计算是基于时间间隔和令牌生成速率，用于更新令牌桶状态。
redis.call('HSET', token, 'last_time', now)                                       -- 更新哈希表中的上一次操作时间为当前时间。
if tokensLeft >= 1 then
    redis.call('HSET', token, 'tokens_left', tokensLeft - 1)                      -- 在Redis中更新剩余令牌数量，将其减1。
    return true                                                                   -- 返回 true，表示操作可以执行
end

return false
