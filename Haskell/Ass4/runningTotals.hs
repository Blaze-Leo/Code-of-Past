import Control.Monad (when)
import System.Exit (exitSuccess)

main :: IO ()
main = do
  input <- getContents
  let inputLines = lines input
  let numbers = map read (filter (not . null) inputLines) :: [Integer]
  when (null numbers) $ do
    exitSuccess
  let runningTotals = scanl1 (+) numbers
  mapM_ putStrLn (zipWith (\a b -> show a ++ " + " ++ show b ++ " = " ++ show (a + b)) (0 : init runningTotals) numbers ++ ["Final total = " ++ show (last runningTotals)])
