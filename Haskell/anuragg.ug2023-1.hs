choose :: Integer -> Integer -> Integer
choose _ 0 = 1
choose n r = choose n (r - 1) * (n - r + 1) `div` r

-------------------------------

primeSum :: Int -> Integer
primeSum n = primes [2..(toInteger n)]

primes :: [Integer] -> Integer
primes [] = 0
primes (p : xs) = p + primes [x | x <- xs, x `mod` p /= 0]

-------------------------------

leftRotate :: Integer -> Integer
leftRotate n = (n `mod` d ) * 10 + (n `div` d)
   where d = 10 ^ (count n -1)

count :: Integer -> Integer
count 0 = 0
count n = 1+count (n`div` 10)

-------------------------------

cLength :: Integer -> Maybe Int
cLength n = cLengthExtend n 0

cLengthExtend :: Integer -> Int -> Maybe Int
cLengthExtend 1 k = Just k
cLengthExtend  n k
   |  k >= 9999 = Nothing
   |  even n = cLengthExtend (n `div` 2) (k+1)
   |  otherwise = cLengthExtend (3*n+1) (k+1)

-------------------------------

frac :: (Int, Int, Int, Int, Int) -> Maybe (Int, Int, Int)
frac (s,x,y,z,w)
   |  s/=1 && s/= -1 =  Nothing
   |  x<0 = Nothing
   |  y<0 || z<0 || w<0 = Nothing
   |  y>9 || z>9 || w>9 = Nothing
   |  otherwise = if s==1 then Just (y,z,w) else Just (fracNeg (y,z,w))

fracNeg :: (Int,Int,Int) -> (Int, Int,Int)
fracNeg (x,y,z) = (d `div` 100, d `div` 10 - (d `div` 100)*10, d `mod` 10)
   where d = (1000 - (x*100+y*10+z)) `mod` 1000
-------------------------------

ilog :: Integer -> Integer -> Integer
ilog n k =  ilogExtend n k 0

ilogExtend :: Integer -> Integer -> Integer -> Integer
ilogExtend n k p =
   if k^p <= n
   then ilogExtend n k (p+1)
   else p-1

-------------------------------

cos1 :: Integer -> Double -> Double
cos1 n x = addCos (take (fromInteger n) [(((-1)**t)*(x**(2*t)))/(fact (2*t)) | t<-[0..]]) 0.0

cos2 :: Double -> Double -> Double
cos2 e x = cos2Extend 1 x 2

cos2Extend :: Integer -> Double -> Double -> Double
cos2Extend n x c =   if cos1 n x == c
                     then cos1 n x
                     else cos2Extend (n+1) x (cos1 n x)


addCos :: [Double] -> Double -> Double
addCos [] s= s
addCos (x:xs) s= if x==0
               then s
               else addCos xs (x+s)

fact :: Double -> Double
fact 0=1
fact s=s * fact (s-1)