import Data.List

-- Problem 1.
-------------
vanadurga :: (Int, Int, Int) -> ([Int], [Int])
vanadurga (m, r, n) = vanHelper (m, r) ([], [1 .. n])

vanHelper (_, 0) (dead, n) = (dead, sort n)
vanHelper (m, r) (dead, n) = vanHelper (m, r - 1) (dead ++ [n !! (d - 1)], vanDel n d) where d = if (m `mod` length n) == 0 then length n else m `mod` length n

vanDel n d = drop d n ++ take (d - 1) n

-- Problem 2.
-------------

data Tree = E | NE T

data T = Leaf Int | Branch (Int, Int) T T

instance Show Tree where
  show = drawTree



tMax :: T -> Int
tMax (Leaf x) = x
tMax (Branch (_, maxRight) _ _) = maxRight

tMin :: T -> Int
tMin (Leaf x) = x
tMin (Branch ( minLeft,_) _ _) = minLeft

inorder :: Tree -> [Int]
inorder E = []
inorder (NE tree) = inorderTree tree
  where
    inorderTree :: T -> [Int]
    inorderTree (Leaf x) = [x]
    inorderTree (Branch _ left right) = inorderTree left ++ inorderTree right

tSearch :: Int -> Tree -> Bool
tSearch _ E = False
tSearch x (NE tree) = searchTree x tree
  where
    searchTree :: Int -> T -> Bool
    searchTree x' (Leaf y) = x' == y
    searchTree x' (Branch _ left right)
      | x' <= tMax left = searchTree x' left
      | x' >= tMin right = searchTree x' right
      | otherwise = False

tInsert :: Int -> Tree -> Tree
tInsert x E = NE (Leaf x)
tInsert x (NE tree) = if tSearch x (NE tree) then NE tree else NE (insertTree x tree)
  where
    insertTree :: Int -> T -> T
    insertTree x' (Leaf y)
      | x' < y = Branch (x', y) (Leaf x') (Leaf y)
      | otherwise = Branch (y, x') (Leaf y) (Leaf x')
    insertTree x' (Branch bounds@(minL, maxR) left right)
      | x' < minL = Branch (x', maxR) (insertTree x' left) right
      | x' > maxR = Branch (minL, x') left (insertTree x' right)
      | otherwise = if withinChecker x' left right then Branch bounds (insertTree x' left) right else Branch bounds (nextChecker x' left) (if leftDone left then right else nextChecker x' right)

    nextChecker x' (Leaf y) = insertTree x' (Leaf y)
    nextChecker x' (Branch bounds@(minL, maxR) left right)
      | x' > minL && x' < maxR = insertTree x' (Branch bounds left right)
      | otherwise = Branch bounds left right

    withinChecker n (Leaf x) (Leaf y) = n>x && n<y
    withinChecker n (Branch (_,b) _ _) (Leaf y) = n>b && n<y
    withinChecker n (Leaf x) (Branch (a,_) _ _) = n>x && n<a
    withinChecker n (Branch (_,b) _ _) (Branch (c,_) _ _) = n>b && n<c

    leftDone (Leaf _) = True
    leftDone _ = False

tDelete :: Int -> Tree -> Tree
tDelete _ E = E
tDelete x (NE (Leaf y)) = if x==y then E else NE (Leaf y)
tDelete x (NE tree) = NE (deleteTree x tree)
  where
    deleteTree :: Int -> T -> T
    deleteTree x' (Branch bounds@(minL, maxR) (Leaf x) (Leaf y))
      | x'==x = Leaf y
      | x'==y = Leaf x
      | otherwise = Branch bounds (Leaf x) (Leaf y)
    deleteTree x' (Branch bounds@(minL, maxR) (Leaf x) right) = if x'==x then right else  Branch bounds (Leaf x) (deleteTree x' right)
    deleteTree x' (Branch bounds@(minL, maxR) left (Leaf x)) = if x'==x then left else Branch bounds (deleteTree x' left) (Leaf x)
    deleteTree x' (Branch bounds@(minL, maxR) left right)
      | x' >= minL && x' <= maxR = Branch bounds (deleteTree x' left) (deleteTree x' right)
      | otherwise = Branch bounds left right

fromList :: [Int] -> Tree
fromList = foldr tInsert E

drawTree :: Tree -> String
drawTree E = "|\n*"
drawTree (NE t) = unlines lines
  where
    (lines, _, _) = go t
    go :: T -> ([String], Int, Int)
    go (Leaf x) = ([spike, sx], p, q)
      where
        (sx, lenx, p, q) = (show x, length sx, lenx `div` 2, lenx - p - 1)
        spike = replicate p ' ' ++ "|" ++ replicate q ' '
    go (Branch (a,b) tl tr) = (line1 : line2 : rest, m, n)
      where
        (linesl, ml, nl) = go tl
        (linesr, mr, nr) = go tr
        (m, n) = (ml + nl + 2, mr + nr + 2)
        line1 = replicate (ml + nl + 2) ' ' ++ "|" ++ replicate (mr + nr + 2) ' '
        line2 =
          replicate ml ' '
            ++ "+"
            ++ replicate (nl + 1) '-'
            ++ "+"
            ++ replicate (mr + 1) '-'
            ++ "+"
            ++ replicate nr ' '
            ++ "("++show a ++","++show b++")"
        rest = join linesl linesr
        join xss yss
          | null xss = map (replicate (ml + nl + 4) ' ' ++) yss
          | null yss = map (++ replicate (mr + nr + 4) ' ') xss
          | otherwise = (xs ++ replicate 3 ' ' ++ ys) : join xss' yss'
          where
            (xs : xss') = xss
            (ys : yss') = yss

-- main = do
--   print $ vanadurga (3, 10, 15) == ([3, 6, 9, 12, 15, 4, 8, 13, 2, 10], [1, 5, 7, 11, 14])
--   print $ vanadurga (2, 19, 20) == ([2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 3, 7, 11, 15, 19, 5, 13, 1, 17], [9])
--   print $ vanadurga (2, 0, 20) == ([], [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20])
--   print $ vanadurga (9, 8, 10) == ([9, 8, 10, 2, 5, 3, 4, 1], [6, 7])