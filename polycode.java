class Animals {
    void sound() {
        System.out.println("Animal sound");
    }
}
class Dogs extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
}
class polycode {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.sound();
    }
}