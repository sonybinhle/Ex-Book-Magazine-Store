package com.xeasony.service.base;

import com.xeasony.repository.base.IRepository;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public abstract class IService<T> {
    protected IRepository<T> repository;

    public IService(IRepository<T> repository) {
        this.repository = repository;
    }

    public T find(T entity) {
        return repository.find(entity);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T add(T entity) {
        return repository.add(entity);
    }

    public void readFromCsvFile(String filePath, Charset charset, IParse<T> parse) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), charset.toString()));
            br.lines()
                    .skip(1)
                    .forEach((line) -> {
                        T entity = parse.parseFromLine(line);
                        add(entity);
                    });
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
