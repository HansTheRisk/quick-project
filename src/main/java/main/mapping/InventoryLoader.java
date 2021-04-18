package main.mapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class InventoryLoader {

    private final ObjectMapper objectMapper;
    private final File jsonFile;
    private CategoryMapping inventory;
    
    public InventoryLoader(File jsonFile) {
        this.jsonFile = jsonFile;
        this.objectMapper = new ObjectMapper();
    }

    public InventoryLoader(File jsonFile, ObjectMapper objectMapper) {
        this.jsonFile = jsonFile;
        this.objectMapper = objectMapper;
    }
    
    public CategoryMapping readInventory() throws IOException {
        if(inventory == null) {
            inventory = objectMapper.readValue(jsonFile, CategoryMapping.class);
            inventory.setRoot(true);
        }
        return inventory;
    }
}
