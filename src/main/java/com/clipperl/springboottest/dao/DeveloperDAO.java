package com.clipperl.springboottest.dao;


import com.clipperl.springboottest.model.DeveloperModel;

import java.util.List;

public interface DeveloperDAO {
    List<DeveloperModel> getAllDevelopers();

    DeveloperModel getDeveloper(String developerId);

    boolean addDeveloper(DeveloperModel developerModel);

    boolean updateDeveloper(String id, String name);

    boolean deleteDeveloper(String id);
}
