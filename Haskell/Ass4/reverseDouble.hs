import Control.Monad
import System.Exit
import System.IO

main :: IO ()
main = instruct

instruct :: IO ()
instruct = do
  putStr "Enter an instruction (R/D/RD/Q): "
  hFlush stdout
  eof <- isEOF
  if eof
    then exitSuccess
    else do
      instr <- getLine
      case instr of
        "R" -> do
          compute "R"
          instruct
        "D" -> do
          compute "D"
          instruct
        "RD" -> do
          compute "RD"
          instruct
        "Q" -> putStrLn "\t\tEnding session. Bye!"
        _ -> do
          putStrLn "\t\tNot an instruction. Please try again!"
          instruct

compute :: String -> IO ()
compute instr = do
  putStr "Enter a string: "
  hFlush stdout
  eof <- isEOF
  if eof
    then exitSuccess
    else do
      str <- getLine
      let result = case instr of
            "R" -> reverse str
            "D" -> str ++ str
            "RD" -> reverse str ++ str
      putStrLn ("\t\tThe result is: " ++ result)
