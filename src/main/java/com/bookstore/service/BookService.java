package com.bookstore.service;

import com.bookstore.entity.Book;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

public interface BookService {
  List<Book> getBooks();

  PageInfo<Book> getBooksByPage(Integer num);

  Book getBookByBookId(Integer bookId);

  void deleteBookByBookId(Integer bookId);

  void addBook(Map<String, String> params);

  List<Book> getBookByName(String name);

  void updateBook(Map<String,String> params);
}
