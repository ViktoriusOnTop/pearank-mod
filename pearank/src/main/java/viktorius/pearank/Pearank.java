package viktorius.pearank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Pearank implements ModInitializer {
    public static final String MOD_ID = "pearank";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private String defaultConfigString;
    private String loadedConfigString;

    private Config defaultConfig = new Config();

    private List<Rank> loadedRanks = new ArrayList<Rank>();

    @Override
    public void onInitialize() {
        LOGGER.info("Pearank Initializing");
        defaultConfig.setDefaultRank();
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("pearank.json");

        if (Files.exists(configPath)) {
            //read as string
            try {
                loadedConfigString = Files.readString(configPath);

                Gson gson = new Gson();
                Config config = gson.fromJson(loadedConfigString, Config.class);
                loadedRanks = config.getRanks();
            } catch (IOException ex) {
                throw new RuntimeException("exception: ", ex);
            }
            //convert to list
            //reference below code
        } else{
            loadedRanks = defaultConfig.getRanks();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try {
                defaultConfigString = gson.toJson(defaultConfig);
            } catch (Exception e) {
                LOGGER.error("Failed to save config", e);
            }
            try (FileWriter writer = new FileWriter(configPath.toFile())){
                JsonElement jsonElement = JsonParser.parseString(defaultConfigString);
                gson.toJson(jsonElement, writer);
                LOGGER.info("config made");
            }catch (IOException e){
                LOGGER.error("Config not made, Error goes as follows:", e);
            }
        }

        LOGGER.info(String.valueOf(findIfHasPerms("Viktorius_", "test.perm")));
        LOGGER.info(String.valueOf(findIfHasPerms("Viktorius_", "test.perm2")));
        LOGGER.info(String.valueOf(findIfHasPerms("GlitchedWinner", "test.perm")));
        LOGGER.info(String.valueOf(findIfHasPerms("GlitchedWinner", "test.perm2")));

        //checks if pearank.config is located in config folder
        //if yes then loads it into memory for quick access
        //if no then creates one with default settings
        LOGGER.info("Welcome to Pearank");
    }


    public boolean findIfHasPerms(String playerName, String permission) {
        for (Rank rank : loadedRanks) {
            List<String> members = rank.getRankMembers();
            if (members.contains(playerName)) {
                List<String> perms = rank.getPermissions();
                return perms.contains(permission);
            }
        }

        for (Rank rank : loadedRanks) {
            List<String> members = rank.getRankMembers();
            if (members.contains("thisIsTheDefaultRank")) {
                List<String> perms = rank.getPermissions();
                return perms.contains(permission);
            }
        }

        return false;
    }
}