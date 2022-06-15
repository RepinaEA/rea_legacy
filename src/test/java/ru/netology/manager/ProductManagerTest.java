package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductManagerTest {

    Product first = new Smartphone(101, "Iphone 11", 70000, "Apple");
    Product second = new Smartphone(202, "Samsung Galaxy", 100000, "Samsung");
    Product third = new Smartphone(303, "Nokia", 130000, "Nokia");
    Product fourth = new Book(404, "Pyramids", 5000, "Terry Pratchett");
    Product fifth = new Book(505, "Karlsson-on-the-Roof", 1500, "Astrid Lindgren");
    Product sixth = new Book(606, "Harry Potter", 7000, "J. K. Rowling");
    Product seventh = new Book(707, "Carrie", 700, "Stephen King");

    @Test
    public void AddProduct() {
        ProductManager add = new ProductManager(new ProductRepository());
        add.add(first);
        add.add(second);
        add.add(third);
        add.add(fourth);
        add.add(fifth);
        add.add(sixth);
        add.add(seventh);

        Product[] actual = add.findAll();
        Product[] expected = {first, second, third, fourth, fifth, sixth, seventh};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void NoAddProduct() {
        ProductManager add = new ProductManager(new ProductRepository());

        Product[] actual = add.findAll();
        Product[] expected = {};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void RemoveProductById() {
        ProductManager add = new ProductManager(new ProductRepository());
        add.add(first);
        add.add(second);
        add.add(third);
        add.add(fourth);
        add.add(fifth);
        add.add(sixth);
        add.add(seventh);

        Product[] actual = add.removeById(505);
        Product[] expected = {first, second, third, fourth, sixth, seventh};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void RemoveProductByFalseId() {
        ProductManager add = new ProductManager(new ProductRepository());
        add.add(first);
        add.add(second);
        add.add(third);
        add.add(fourth);
        add.add(fifth);
        add.add(sixth);
        add.add(seventh);

        assertThrows(NotFoundException.class, () -> {
            add.removeById(808);
        });
    }

    @Test

    public void searchProductByString() {

        ProductManager add = new ProductManager(new ProductRepository());
        add.add(first);
        add.add(second);
        add.add(third);
        add.add(fourth);
        add.add(fifth);
        add.add(sixth);
        add.add(seventh);

        Product[] actual = add.searchBy("y");
        Product[] expected = {second, fourth, sixth};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchProductByFalseString() {
        ProductManager add = new ProductManager(new ProductRepository());
        add.add(first);
        add.add(second);
        add.add(third);
        add.add(fourth);
        add.add(fifth);
        add.add(sixth);
        add.add(seventh);

        Product[] actual = add.searchBy("book");
        Product[] expected = {};

        Assertions.assertArrayEquals(expected, actual);
    }
}
