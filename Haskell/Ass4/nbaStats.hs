import Data.List
import System.Environment

main :: IO ()
main = do
  cont <- getContents
  let line = addSame $ isort $ map ((\[a, b] -> if head a == '@' then [tail a, "A " ++ b, "0", "0", win b, loss b] else [a, "H " ++ b, win b, loss b, "0", "0"]) . words) (lines cont)
  let endS = foldl (\[hw1, hl1, aw1, al1] [t, s, hw2, hl2, aw2, al2] -> [adder hw1 hw2, adder hl1 hl2, adder aw1 aw2, adder al1 al2]) ["0", "0", "0", "0"] line
  mapM_ putStrLn (map format line ++ ["     -------", ender endS, "     -------"])

win s = if (read (takeWhile (/= '-') s) :: Int) > (read (tail (dropWhile (/= '-') s)) :: Int) then "1" else "0"

loss s = if (read (takeWhile (/= '-') s) :: Int) < (read (tail (dropWhile (/= '-') s)) :: Int) then "1" else "0"

addSame [[]] = [[]]
addSame [s] = [s]
addSame (x : y : xs) = if head x == head y then addSame ((\[t, s1, hw1, hl1, aw1, al1] [_, s2, hw2, hl2, aw2, al2] -> [t, s1 ++ ", " ++ s2, adder hw1 hw2, adder hl1 hl2, adder aw1 aw2, adder al1 al2]) x y : xs) else x : addSame (y : xs)

adder x y = show ((read x :: Int) + (read y :: Int))

isort = foldr ins []

ins x [] = [x]
ins x (y : ys)
  | head x < head y = x : y : ys
  | otherwise = y : ins x ys

format [t, s, hw, hl, aw, al] = t ++ " : " ++ adder hw aw ++ " - " ++ adder hl al ++ "    " ++ s

ender [hw, hl, aw, al] = "     " ++ adder hw aw ++ " - " ++ adder hl al ++ "    Home: " ++ hw ++ " - " ++ hl ++ ", Away: " ++ aw ++ " - " ++ al
