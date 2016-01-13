package com.adrienf.budget;

import com.adrienf.budget.domain.Category;
import com.adrienf.budget.service.CategoryRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@Service
public class BootstrapData implements InitializingBean {

    private final Logger LOG = LoggerFactory.getLogger(BootstrapData.class);

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    @Transactional()
    public void afterPropertiesSet() throws Exception {
        LOG.info("Bootstrapping...");


    }

    private void createCategories() {
        if (categoryRepository.count() > 0) {
            return;
        }

        LOG.info("Importing categories...");

        ObjectMapper mapper = new ObjectMapper();
        InputStream in = getClass().getResourceAsStream("/categories.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        URL url = Resources.getResource("categories.json");
        try {
            String json = Resources.toString(url, Charsets.UTF_8);
            JsonCategory[] categories = mapper.readValue(json, JsonCategory[].class);
            for (JsonCategory category : categories) {
                Category parent = new Category(category.getName(), category.getIcon(), category.getColor());
                categoryRepository.save(parent);
                for (String s : category.getChildren()) {
                    categoryRepository.save(new Category(s, parent));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class JsonCategory {
        private String name;
        private String icon;
        private String color;
        private List<String> children;

        public JsonCategory(@JsonProperty("name") String name, @JsonProperty("icon") String icon, @JsonProperty("color") String color, @JsonProperty("children") List<String> children) {
            this.name = name;
            this.icon = icon;
            this.color = color;
            this.children = children;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public List<String> getChildren() {
            return children;
        }

        public void setChildren(List<String> children) {
            this.children = children;
        }
    }
}
