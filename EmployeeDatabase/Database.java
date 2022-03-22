package EmployeeDatabase;

class Employee {
  String name;
  int id;
  public Employee(String s, int i) {
    this.name = s;
    this.id = i;
  }

}

public class Database {
  Employee data[] = new Employee[500000];
  public int hash(int key) {
    return (key % 100000 - 10000);
  }
  public void put(int Key, Employee Val) {
    int hash = this.hash(Key);
    if(this.data[hash] == null || this.data[hash].id == Val.id)
      this.data[hash] = Val;
    else {
      linear(hash, Val);
    }
  }
  public Employee get(int Key) {
    int hash = this.hash(Key);
    if(this.data[hash] == null) return null;
    if(this.data[hash].id != Key) {
      return linearGet(hash, Key);
    }
    else return this.data[hash];
  }
  public Employee linearGet(int hash, int ID) {
    while(data[hash].id != ID) {
      ++hash;
      if(data[hash] == null) return null;
    }
    return this.data[hash];
  }
  public Employee quadGet(int hash, int ID) {
    int num = 0;
    while(data[hash + num * num].id != ID) {
       ++num;
       if(data[hash + num * num] == null) return null;
    }
    return data[hash + num * num];
  }
  public void linear(int hash, Employee val) {
    while(this.data[hash] == null)
      ++hash;
    this.data[hash] = val;
  }
  public void quad(int hash, Employee val) {
    int ind = 1;
    while(this.data[hash + ind * ind] == null)
      ++ind;
    this.data[hash + ind * ind] = val;
  }
}
class Tester {
  public static void main(String[] args) {
    Database db = new Database();
    Employee p = new Employee("BOB", 50000);
    int getID = 50000;
    db.put(p.id,p);
    if(db.get(getID) == null) {
      System.out.println("No Entry For ID: " + getID);
    } else {
      System.out.print("Entry Found for ID: " + db.get(getID).id + " -> " + db.get(getID).name);
    }
  }
}