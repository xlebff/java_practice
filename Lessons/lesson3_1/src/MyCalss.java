class MyClass implements InterfaceA, InterfaceB {
    @Override public void sayHello() {
        System.out.println("Мой класс решает, какой метод вызвать:");
        InterfaceA.super.sayHello();
        InterfaceB.super.sayHello();
    }
} // Основной класс программы