abstract class Animal{
    abstract void sound();
}
class Cat extends Animal{
    void sound(){
        System.out.println("Cat meows");
    }
}
class Dog extends Animal{
    void sound(){
        System.out.println("Dog barks");
    }
}
class polyabcode{
    public static void main(String[] args){
        Animal a1 = new Cat();
        Animal a2 = new Dog();
        a1.sound();
        a2.sound();
    }
}