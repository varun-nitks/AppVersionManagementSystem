package com.example.project1.repository;

import com.example.project1.domain.App;
import java.util.*;

public class AppRepository {
    private Map<String, App> apps = new HashMap<>();

    public void save(App app) {
        apps.put(app.getAppId(), app);
    }

    public App findById(String appId) {
        return apps.get(appId);
    }

    public List<App> findAll() {
        return new ArrayList<>(apps.values());
    }
}
