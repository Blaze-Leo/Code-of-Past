{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use head" #-}
import Data.Int (Int8)
import Data.List (foldl')
import Data.Ratio (denominator, numerator, (%))
import Data.Word (Word8)

-- Problem 1
-- Check if input list is an AP. Length <= 2 means it is a trivial AP.
isAP :: [Integer] -> Bool
isAP [] = True
isAP [_]=True
isAP [_,_]=True
isAP n = apHelp (n !! 0) (n !! 1 - n !! 0) 0 n
  where
    apHelp :: Integer -> Integer -> Integer -> [Integer] -> Bool
    apHelp a d c [] = True
    apHelp a d c (s : xs) = (s == (a + c * d)) && apHelp a d (c + 1) xs

-- Problem 2
-- targetSum t l checks if two elements at distinct positions of l sum to t
targetSum :: Integer -> [Integer] -> Bool
targetSum t [] = False
targetSum t (x : xs) = goon (t - x) xs || targetSum t xs
  where
    goon :: Integer -> [Integer] -> Bool
    goon t [] = False
    goon t (x : xs) = (t == x) || goon t xs

-- Problem 3
-- increment a list of Word8-s representing an integer
incr :: [Word8] -> [Word8]
incr x = incrHelp x True
  where
    incrHelp :: [Word8] -> Bool -> [Word8]
    incrHelp [] b = [1 | b]
    incrHelp x b = incrHelp (init x) (b && last x == 255) ++ if b then if last x == 255 then [0] else [last x + 1] else [last x]

-- Problem 4
-- Find Int8 value represented by list of 8 bits (in twos complement form), and find twos complement representation (consisting of 8 bits) of an Int8.
twosVal :: [Bool] -> Int8
twosVal t = bint (tail t) 6 - (if head t then 128 else 0)
  where
    bint :: [Bool] -> Int8 -> Int8
    bint [] _ = 0
    bint (x : xs) n = (if x then 2 ^ n else 0) + bint xs (n - 1)

twosRep :: Int8 -> [Bool]
twosRep n = if n < 0 then True : ina (n + 128) 6 else False : ina n 6
  where
    ina :: Int8 -> Int8 -> [Bool]
    ina _ (-1) = []
    ina t n = if t >= (2 ^ n) then True : ina (t - (2 ^ n)) (n - 1) else False : ina t (n - 1)

-- Problem 5
-- Compute a finite continued fraction representing a rational, and the rational represented by a finite continued fraction.
invert :: Rational -> Rational
invert x = denominator x % numerator x

cf :: Rational -> [Integer]
cf c
  | denominator c == 1 = [numerator c]
  | abs (numerator c) > abs (denominator c) = (numerator c `div` denominator c) : cf (denominator c % (numerator c `mod` denominator c))
  | otherwise = [denominator c]
  
computeRat :: [Integer] -> Rational
computeRat n = invert (rat n (0 % 1))
  where
    rat :: [Integer] -> Rational -> Rational
    rat [] c = c
    rat x c = rat (init x) (invert ((last x * denominator c + numerator c) % denominator c))

-- Problem 6
-- Compute approximations of sqrt 15, based on infinite continued fraction.
computeFrac :: Rational -> Double
computeFrac x = fromIntegral (numerator x) / fromIntegral (denominator x)

approxTarget :: Double -> Rational
approxTarget e = cont e 3
  where
    cont :: Double -> Integer -> Rational
    cont e n = if abs (computeFrac (computeRat (3 : create16 (n - 1))) - computeFrac (computeRat (3 : create16 n))) < e then computeRat (3 : create16 n) else cont e (n + 1)

create16 :: Integer -> [Integer]
create16 n
  | n >= 2 = [1, 6] ++ create16 (n - 2)
  | n == 1 = [1]
  | otherwise = []