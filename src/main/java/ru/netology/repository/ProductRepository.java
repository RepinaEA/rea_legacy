package ru.netology.repository;

import ru.netology.domain.Product;

public class ProductRepository {
    private Product[] products = new Product[0];

    public ProductRepository() {
    }

    public void save(Product product) {
        int length = products.length + 1;
        Product[] tmp = new Product[length];

        System.arraycopy(products, 0, tmp, 0, products.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = product;
        products = tmp;
    }

    public Product[] findAll() {
        return products;
    }

    public Product[] removeById(int id) {
        if (findById(id) != null) {
            int length = products.length - 1;
            Product[] tmp = new Product[length];
            int index = 0;
            for (Product product : products) {
                if (product.getId() != id) {
                    tmp[index] = product;
                    index++;
                }
            }
            products = tmp;
            return products;
        }
        throw new NotFoundException("Идентификатор " + id + " не найден");
    }

    public Product[] findById(int id) {
        Product[] tmp = new Product[products.length];
        int index = 0;
        for (Product product : products) {
            if (product.getId() == id) {
                tmp[index] = product;
                index++;
            }
        }
        Product[] find = new Product[index];
        System.arraycopy(tmp, 0, find, 0, find.length);

        if (find.length != 0) {
            return find;
        }
        return null;
    }

}
