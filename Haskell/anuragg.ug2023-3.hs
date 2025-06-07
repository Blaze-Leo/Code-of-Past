import Data.Array

-- Problem 1
hamming :: (Eq a) => [a] -> [a] -> Maybe Int
hamming x y
  | length x /= length y = Nothing
  | otherwise = Just (ham x y)
  where
    ham :: (Eq a) => [a] -> [a] -> Int
    ham x y
      | null x = 0
      | head x /= head y = 1 + ham (tail x) (tail y)
      | otherwise = ham (tail x) (tail y)

-- Problem 2
parity :: Int -> Bool
parity n = not (foldr1 (/=) (ina n (twoEnd 0)))
  where
    twoEnd k = if 2 ^ k > n then k else twoEnd (k + 1)
    ina _ (-1) = []
    ina t n = if t >= (2 ^ n) then True : ina (t - (2 ^ n)) (n - 1) else False : ina t (n - 1)

-- Problem 3
mss :: [Integer] -> (Integer, [Integer])
mss [] = (0, [])
mss n = if foldl (\x y -> x && (y <= 0)) True n then (0,[]) else mss0 n (mss1 0 0 (map (^ 3) n) 0 (0, 0, 0))

mss0 x (s, a, b) = (s, createPart x a b 0)

createPart x a b i
  | i == length x = []
  | i >= a && i <= b = x !! i : createPart x a b (i + 1)
  | otherwise = createPart x a b (i + 1)

mss1 _ _ [] _ (s, a, b) = (s, a, b)
mss1 i j (x : xs) p (s, a, b)
  | q <= 0 = mss1 (j + 1) (j + 1) xs 0 (s, a, b)
  | q > s = mss1 i (j + 1) xs q (q, i, j)
  | otherwise = mss1 i (j + 1) xs q (s, a, b)
  where
    q = p + x

-- Problem 4
squares :: [(Int, Int)]
squares = [(x, y) | x <- [0 .. 7], y <- [0 .. 7]]

knightExtra (x,y) n = redirect squares $ map (\(x, y, z) -> (x - 10, y - 10)) $ filter (\(x, y, z) -> x > 9 && y > 9) $ knight (x, y, 0) []
  where
    redirect [] _ = []
    redirect (s : xs) l = if s `elem` l then s : redirect xs l else redirect xs l
    knight (x, y, i) l
      | x < 0 || x > 7 || y < 0 || y > 7 || elem (x, y, i) l = l
      | i == n = (x + 10, y + 10, i) : l
      | otherwise = knight (x + 1, y + 2, i + 1) $ knight (x - 1, y + 2, i + 1) $ knight (x + 1, y - 2, i + 1) $ knight (x - 1, y - 2, i + 1) $ knight (x + 2, y + 1, i + 1) $ knight (x - 2, y + 1, i + 1) $ knight (x + 2, y - 1, i + 1) $ knight (x - 2, y - 1, i + 1) ((x, y, i) : l)


knightMove :: (Int, Int) -> Int -> [(Int, Int)]
knightMove (x, y) n
  | even n && n>4 = caller 6
  | odd n && n>3 = caller 5
  | otherwise = caller n
  where
      caller = knightExtra (x, y)

-- Problem 5
editDistance :: String -> String -> (Int, String)
editDistance a b = (cost,reverse edited)
  where
    al = length a
    bl = length b
    costAr = listArray ((0, 0), (al, bl)) [creC i j | i <- [0 .. al], j <- [0 .. bl]]
    creC 0 0 = 0
    creC i 0 = i * 2
    creC 0 j = j * 2
    creC i j
      | a !! (i - 1) == b !! (j - 1) = costAr ! (i - 1, j - 1)
      | otherwise = minimum [costAr ! (i, j - 1) + 2, costAr ! (i - 1, j) + 2, costAr ! (i - 1, j - 1) + 1]

    cost = costAr ! (al, bl)
    edited = creS al bl

    creS i j
      | i == 0 && j == 0 = []
      | i > 0 && costAr ! (i, j) == costAr ! (i - 1, j) + 2 = 'd' : creS (i - 1) j
      | j > 0 && costAr ! (i, j) == costAr ! (i, j - 1) + 2 = 'i' : creS i (j - 1)
      | i > 0 && j > 0 && costAr ! (i, j) == costAr ! (i - 1, j - 1) + 1 = 'm' : creS (i - 1) (j - 1)
      | otherwise = '-' : creS (i - 1) (j - 1)

main = do
  print 1
  print $ hamming "" "" == Just 0
  print $ hamming "" "haskell" == Nothing
  print $ hamming "haskell" "haskell" == Just 0
  print $ hamming "Haskell" "haskell" == Just 1
  print $ hamming "Happens" "haskell" == Just 5
  print $ hamming "ocaml" "haskell" == Nothing
  print 2
  print $ parity 0 == True
  print $ parity 10 == True 
  print $ parity 100 == False 
  print $ parity 1000 == True 
  print $ parity 2289 == True 
  print 3
  print $ mss [] == (0,[])
  print $ mss [2,-4,2,-3,16,-16,0,-13,-6,11] == (4096,[16])
  print $ mss [-10,1,-1,15,7,-20,-12,17,18,-6] == (10745,[17,18])
  print $ mss [-17,6,6,-3,-3,20,-8,-13,-4,13] == (8378,[6,6,-3,-3,20])
  print 4
  print $ knightMove (0,0) 0 == [(0,0)]
  print $ knightMove (0,0) 1 == [(1,2),(2,1)]
  print $ knightMove (0,0) 3 == [(0,1),(0,3),(0,5),(1,0),(1,2),(1,4),(1,6),(2,1),(2,3),(2,5),(3,0),(3,2),(3,4),(3,6),(4,1),(4,3),(4,5),(5,0),(5,2),(5,4),(6,1),(6,3)]
  print $ knightMove (2,2) 2 == [(0,2),(0,6),(1,1),(1,3),(1,5),(2,0),(2,2),(2,4),(2,6),(3,1),(3,3),(3,5),(4,2),(4,6),(5,1),(5,3),(5,5),(6,0),(6,2),(6,4)]
  print $ knightMove (2,2) 1000 == [(0,0),(0,2),(0,4),(0,6),(1,1),(1,3),(1,5),(1,7),(2,0),(2,2),(2,4),(2,6),(3,1),(3,3),(3,5),(3,7),(4,0),(4,2),(4,4),(4,6),(5,1),(5,3),(5,5),(5,7),(6,0),(6,2),(6,4),(6,6),(7,1),(7,3),(7,5),(7,7)]
  print 5
  print $ editDistance "kitten" "sitting"== (4,"m---m-i")
  print $ editDistance "Kareem was the leading scorer" "LeBron is the scoring leader"== (17,"mmmmmm-md------mmmm----mmmm--")
  print $ editDistance "Jordan was an all-time great" "LeBron is even greater"== (28,"mmmmm--md--mmmmmmmmmm-dd-ddd")
  print $ editDistance "LeBron is even greater" "Jordan was an all-time great"== (28,"mmmmm--mi--mmmmmmmmmm-ii-iii")
  print $ editDistance "" "This is a nonempty string"== (50,"iiiiiiiiiiiiiiiiiiiiiiiii")
  print $ editDistance "This is a nonempty string" ""== (50,"ddddddddddddddddddddddddd")
  print $ editDistance "szwyjbvzflvqtdk" "npagbpcwbptbvrnbifxt"== (24,"mmmmmmmmmmii-mmmmiii")
  print $ editDistance "fittingly" "misfitting"== (9,"m-mm-mmmmi")
  print $ editDistance "fittingly" "unfitting"== (8,"ii-------dd")