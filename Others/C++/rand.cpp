// Name:
// Project:
// Due Date:
// Filename: employee-raise.cpp
// Description: The program takes a file a reads the employee name, their current salary, and the percentage increase in salary.
//              Then it calculates the new salary and writes the employee names in the correct format and the new salary in a new .dat file.

#include <iostream>
#include <fstream>
#include <iomanip>
#include <string>

using namespace std;

int main() {
    // Input and output file names
    string inputFileName = "employee-data.txt";
    string outputFileName = "employee-report.dat";

    // Open input file
    ifstream inputFile(inputFileName);

    // Open output file
    ofstream outputFile(outputFileName);

    // Read employee data, calculate new salary, and write to output file
    string lastName, firstName;
    double currentSalary, percentIncrease, newSalary;

    while (inputFile >> lastName >> firstName >> currentSalary >> percentIncrease) {
        //calculate the salary by the given percentage
        newSalary = currentSalary + (currentSalary * percentIncrease / 100);

        // Write to output file with two decimal places
        outputFile << fixed << setprecision(2);
        outputFile << firstName << " " << lastName << " " << newSalary << endl;
    }

    // Close files
    inputFile.close();
    outputFile.close();

    return 0;
}
