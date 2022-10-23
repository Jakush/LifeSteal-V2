# LifeSteal-V2
This is version 2 of LifeSteal because i corrupted version 1 by mistake.

# API
You can access API making variable like this:
```java
LifeStealAPI api = new LifeStealAPI();
```
And under this line, you can see all methods and events available from this API.


## All available methods:
```java
List<Material> getRecipe(String itemStack); ItemStack = beacon or heart;
/**
* @param itemStack Beacon or heart
* @return All 9 materials (can return null)
*/
List<String> getEliminatedPlayers();
/**
* @return All eliminated players
*/
List<Hologram> getHologramStorage();
/**
* @return Hologram storage (advanced)
*/
List<Player> getEditors();
/**
* @return All players who have enabled editing recipes
*/
HashMap<String, Double> getSendAmountStorage();
/**
* @return Every player with player's name and amount that is used in /lifesteal send command (advanced)
*/
String getPluginVersion();
/**
* @return Plugin version
*/
String getPluginAuthor();
/**
* @return Plugin author (normally spigot returns List<String> but we know, there is only one author so it can be String)
*/
String getPluginName();
/**
* @return Plugin name
*/
String getAPIVersion();
/**
* @return Plugin API version
*/
```

## All available events:
```java
PlayerEatHeartEvent(); - Player getPlayer(), ItemStack getItem(), boolean isCancelled(), void setCancelled()
PlayerReviveEvent(); - Player getPlayer(), boolean isCancelled(), void setCancelled();
CommandUseEvent(); - CommandSender getSender(), Player getPlayer(), String[] getArgs(), boolean isCancelled(), void setCancelled()
```
