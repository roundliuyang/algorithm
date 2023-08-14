-- 滑动时间窗口算法分布式版本,借助 Redis + Lua 来实现的

local token = KEYS[1]                -- 获取传入的参数中的令牌标识
local now = tonumber(ARGV[1])        -- 获取当前时间戳，并将其转换为数值。
local window = tonumber(ARGV[2])     -- 获取滑动窗口的时间窗口大小，即允许操作的时间范围（以秒为单位），并将其转换为数值。
local limit = tonumber(ARGV[3])      -- 获取时间窗口内允许的最大操作次数，并将其转换为数值。

local clearBefore = now - window     -- 计算清除操作时间窗口之前的时间戳，以便在Redis中删除旧的操作记录
redis.call('ZREMRANGEBYSCORE', token, 0, clearBefore)   -- 从有序集合中删除时间戳小于等于 clearBefore 的成员，这样可以保持时间窗口内的操作记录。

local amount = redis.call('ZCARD', token)  -- 获取有序集合中的成员数量，即当前时间窗口内的操作次数。
if amount < limit then               -- 如果当前时间窗口内的操作次数小于最大允许次数 limit，表示还可以继续执行操作。
    redis.call('ZADD', token, now, now)    -- 将当前时间戳作为成员添加到有序集合中，同时使用当前时间戳作为分值，以记录新的操作。
end
redis.call('EXPIRE', token, window)  -- 设置有序集合的过期时间为时间窗口大小，确保旧的操作记录在窗口时间后会被自动删除。

return limit - amount                -- 返回剩余的操作次数，即还可以执行的操作次数。这个值可能为正数（还可以继续操作）或零（达到最大操作次数限制）。
