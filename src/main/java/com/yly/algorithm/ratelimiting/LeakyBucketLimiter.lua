--漏斗算法分布式版本,借助 Redis + Lua 来实现的

local token = KEYS[1]                  -- 从传入的 Redis keys 中获取漏斗的标识符（token）
local now = tonumber(ARGV[1])          -- 从传入的 Redis arguments 中获取当前的时间戳（以秒为单位）。

local rate = tonumber(ARGV[2])         -- 从传入的 Redis arguments 中获取漏斗的放水速率（以单位时间内的请求数量为基准）。
local capacity = tonumber(ARGV[3])     --从传入的 Redis arguments 中获取漏斗的容量（最大允许存储的请求数量）。

local lastTime = tonumber(redis.call('HGET', token, 'last_time') or 0)      -- 从 Redis 中获取上一次操作的时间戳，如果没有则默认为 0。
local tokensPend = tonumber(redis.call('HGET', token, 'tokens_pend') or 0)  -- 从 Redis 中获取当前漏斗中待处理的令牌数量，如果没有则默认为 0。

tokensPend = math.max(0, tokensPend - (now - lastTime) * rate)
redis.call('HSET', token, 'last_time', now)                                      -- 更新漏斗的最后操作时间为当前时间。
if tokensPend < capacity then
    -- 如果漏斗中待处理的令牌数量小于容量
    redis.call('HSET', token, 'tokens_pend', tokensPend + 1)                     -- 将待处理的令牌数量增加 1
    return true                                                                  -- 返回 true，表示请求被允许通过。
end

return false

-- 优点：可以看出漏斗的的漏出速率是固定的，可以起到整流的作用
-- 缺点：不能解决流量突发的问题，因为漏出的速率是固定的