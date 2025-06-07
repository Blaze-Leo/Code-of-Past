josephus :: [a] -> Int -> [a]
josephus [a] _ = [a]
josephus xs k = xs !! p : josephus (relast xs p 0 ++ refirst xs p 0) k
  where
    p = ((k - 1) `mod` (length xs))

relast :: [a] -> Int -> Int -> [a]
relast [] _ _ = []
relast (x : xs) k p =
  if (p > k)
    then x : relast xs k (p + 1)
    else relast xs k (p + 1)

refirst :: [a] -> Int -> Int -> [a]
refirst [] _ _ = []
refirst (x : xs) k p =
  if (p < k)
    then x : refirst xs k (p + 1)
    else []

black :: Int
black = get 8
  where
    get :: Int -> Int
    get _ = 99

padic :: Int -> Int -> (Int, [Int])
padic n p = ((maxout n p (-1)), extend n p (maxout n p (-1)))

extend :: Int -> Int -> Int -> [Int]
extend 0 _ _ = []
extend n p k =  (n `div` (p ^ k)) : extend (n `mod` (p ^ k)) p (k - 1) 

maxout :: Int -> Int -> Int -> Int
maxout n p k= if (p^(k+1)) <= n then maxout n p (k+1) else k

pallSub:: String -> Bool
pallSub n = loop1 n 0 (length n)

loop1:: String -> Int -> Int -> Bool
loop1 s i n = if (i==n) then False else (if (loop2 s i i n) then True else loop1 s (i+1) n) 

loop2:: String -> Int -> Int -> Int -> Bool
loop2 s i j n = if (j==n) then False else (if (pallCheck (slice i j s)) then True else loop2 s i (j+1) n)

pallCheck :: String -> Bool
pallCheck n = if (length n == 1) then False else n==(reverse n)

slice :: Int -> Int -> [a] -> [a]
slice from to xs = take (to - from + 1) (drop from xs)