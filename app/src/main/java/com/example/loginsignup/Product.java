package com.example.loginsignup;

import android.widget.ImageView;

public class Product {
    private String category;
    private String name;
    private int price;
    private String owner;
    private ImageView photo;

    public Product(String category, String name, String price, String owner, String photo)
    {    }

    public Product(String category, String name, int price, String owner, ImageView photo)
    {
        this.category = category;
        this.name = name;
        this.price = price;
        this.owner = owner;
        this.photo = photo;
    }

    public String getCategory()   {    return category;    }

    public void setCategory (String category)
    {  this.category = category;  }

    public String getName()  {  return name;    }

    public void setName(String name)  {  this.name = name;  }

    public int getPrice()   {   return price; }

    public void setPrice (int price)

    {   this.price = price;  }


    public String getOwner()   {  return owner;  }

    public void setOwner (String owner)
    {
        this.owner = owner;
    }

    public ImageView getPhoto()   {  return photo;  }

    public void setPhoto(ImageView photo)
    {
        this.photo = photo;
    }

    @Override
    public String toString()
    {
        return "Product {" +
                "category ='" + category + '\'' +
                ", ItemName='" + name + '\'' +
                ", price ='" + price + '\'' +
                ", owner ='" + owner + '\'' +
                ", photo='" + photo +
                '}';
    }
}
