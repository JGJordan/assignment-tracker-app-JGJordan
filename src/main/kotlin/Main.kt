import java.util.*

//Variables
val scanner = Scanner(System.`in`)

//Main
fun main(args: Array<String>) {
}

//Functions
fun mainMenu() : Int {
    print("""
|    ____________________________
|    | Assignment Tracker V1.0  | 
|    |--------------------------|
|    | 1) View Subjects         |                 
|    | 2) Add Subject           |
|    | 3) Update Subject        |
|    | 4) Delete Subject        |
|    |--------------------------|       
|    | 0) Exit Program          |
|    |--------------------------|
    """.trimMargin())
    return scanner.nextInt()
}


