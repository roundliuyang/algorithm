-- 计数器限流算法分布式版本,借助 Redis + Lua 来实现的


-- 1.秒极限流（每秒限制多少请求）：
-- 实现原理
-- 每次请求都将当前时间，精确到秒作为 key 放入 Redis 中
-- 超时时间设置为 2s， Redis 将该 key 的值进行自增
-- 当达到阈值时返回错误，表示请求被限流
-- 写入 Redis 的操作用 Lua 脚本来完成
-- 利用 Redis 的单线程机制可以保证每个 Redis 请求的原子性

-- 资源唯一标志位
local key = KEYS[1]
-- 限流大小
local limit = tonumber(ARGV[1])

-- 获取当前流量大小
local currentLimit = tonumber(redis.call('get', key) or "0")

if currentLimit + 1 > limit then
    -- 达到限流大小 返回
    return 0;
else
    -- 没有达到阈值 value + 1
    redis.call("INCRBY", key, 1)
    -- 设置过期时间
    redis.call("EXPIRE", key, 2)
    return currentLimit + 1
end




-- 2.自定义参数限流（自定义多少时间限制多少请求）：
-- 实现原理
-- 每次请求都去 Redis 取到当前限流开始时间和限流累计请求数
-- 判断限流开始时间加超时时间戳(限流时间)大于当前请求时间戳
-- 再判断当前时间窗口请求内是否超过限流最大请求数
-- 当达到阈值时返回错误，表示请求被限流，否则通过
-- 写入 Redis 的操作用 Lua 脚本来完成
-- 利用 Redis 的单线程机制可以保证每个 Redis 请求的原子性

-- 一个时间窗口开始时间(限流开始时间)key 名称
local timeKey = KEYS[1]
-- 一个时间窗口内请求的数量累计(限流累计请求数)key 名称
local requestKey = KEYS[2]
-- 限流大小，限流最大请求数
local maxRequest = tonumber(ARGV[1])
-- 当前请求时间戳
local nowTime = tonumber(ARGV[2])
-- 超时时间戳，一个时间窗口时间(毫秒)(限流时间)
local timeRequest = tonumber(ARGV[3])

-- 获取限流开始时间，不存在为 0
local currentTime = tonumber(redis.call('get', timeKey) or "0")
-- 获取限流累计请求数，不存在为 0
local currentRequest = tonumber(redis.call('get', requestKey) or "0")

-- 判断当前请求时间戳是不是在当前时间窗口中
-- 限流开始时间加超时时间戳(限流时间)大于当前请求时间戳
if currentTime + timeRequest > nowTime then
    -- 判断当前时间窗口请求内是否超过限流最大请求数
    if currentRequest + 1 > maxRequest then
        -- 在时间窗口内且超过限流最大请求数，返回
        return 0;
    else
        -- 在时间窗口内且请求数没超，请求数加一
        redis.call("INCRBY", requestKey, 1)
        return currentRequest + 1;
    end
else
    -- 超时后重置，开启一个新的时间窗口
    redis.call('set', timeKey, nowTime)
    redis.call('set', requestKey, '0')
    -- 设置过期时间
    redis.call("EXPIRE", timeKey, timeRequest / 1000)
    redis.call("EXPIRE", requestKey, timeRequest / 1000)
    -- 请求数加一
    redis.call("INCRBY", requestKey, 1)
    return 1;
end
