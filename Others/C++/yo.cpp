#include <iostream>

#include <string>

#include <memory>

using namespace std;

class Student {

private:

string m_name;

shared_ptr<Student> m_pFriend;

public:


Student(string name): m_name(name) { }

void makeFriend(shared_ptr<Student> f) { m_pFriend = f; }
};

void function1(shared_ptr<Student> p) {

cout << "B: " << p.use_count() << endl;

}

void function2(shared_ptr<Student>& p) {

cout << "D: " << p.use_count() << endl;
}


void function3(shared_ptr<Student>& p) { 
p->makeFriend(p);

cout << "F: " << p.use_count() << endl;
}


int main() {

auto sp1(make_shared<Student>("Russell"));

cout << "A: " << sp1.use_count() << endl; function1(sp1);

cout << "C: "<< sp1.use_count() << endl;

auto sp2(make_shared<Student>("Singhal")); function2(sp2);

cout << "E: "<< sp2.use_count() << endl;

auto sp3(make_shared<Student>("Ambrosio")); function3(sp3);

cout << "G: "<< sp3.use_count() << endl;

auto sp4(make_shared<Student>("Matos")); weak_ptr<Student> wpS = sp4;

auto sp5=wpS.lock(); cout << "H: " << sp4.use_count() << endl;

}