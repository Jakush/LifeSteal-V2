# LifeSteal-V2
This is version 2 of LifeSteal because i corrupted version 1 by mistake.

# API
You can get API from plugin to recipe.
You just need to add latest version to your plugin.
Then, type in `onEnable()` this
```java
LifeSteal lifesteal = (LifeSteal) Bukkit.getPluginManager().getPlugin("Fine-LifeSteal");
```
## All available methods:
getRecipe(); - returns 9 Materials used in config.

## All available events:
```java
PlayerEatEvent();``` - Player getPlayer(), ItemStack getItem(), boolean isCancelled(), void setCancelled()
```java
PlayerReviveEvent();``` - Player getPlayer(), boolean isCancelled(), void setCancelled();
```java
CommandUseEvent();``` - CommandSender getSender(), Player getPlayer(), String[] getArgs(), boolean isCancelled(), void setCancelled()
