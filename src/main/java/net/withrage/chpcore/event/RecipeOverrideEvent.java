package net.withrage.chpcore.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber
public class RecipeOverrideEvent {

    private static final ResourceLocation VANILLA_NETHERITE =
            ResourceLocation.tryBuild("minecraft", "netherite_ingot");

    @SubscribeEvent
    public static void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener((barrier, resourceManager, preparationsProfiler, reloadProfiler,
                           backgroundExecutor, gameExecutor) -> barrier.wait(null).thenRunAsync(() -> {
            try {
                ReloadableServerResources resources = event.getServerResources();
                RecipeManager recipeManager = resources.getRecipeManager();

                removeRecipe(recipeManager, VANILLA_NETHERITE);

                System.out.println("[CHP Core] Removed vanilla Netherite Ingot recipe.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, gameExecutor));
    }

    @SuppressWarnings("unchecked")
    private static void removeRecipe(RecipeManager recipeManager, ResourceLocation recipeId) throws Exception {
        Field byNameField = RecipeManager.class.getDeclaredField("byName");
        byNameField.setAccessible(true);

        Map<ResourceLocation, Recipe<?>> oldByName =
                (Map<ResourceLocation, Recipe<?>>) byNameField.get(recipeManager);

        Map<ResourceLocation, Recipe<?>> newByName = new HashMap<>(oldByName);
        newByName.remove(recipeId);

        byNameField.set(recipeManager, newByName);


        Field recipesField = RecipeManager.class.getDeclaredField("recipes");
        recipesField.setAccessible(true);

        Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>> oldRecipes =
                (Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>>) recipesField.get(recipeManager);

        Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>> newRecipes = new HashMap<>();

        for (Map.Entry<RecipeType<?>, Map<ResourceLocation, Recipe<?>>> entry : oldRecipes.entrySet()) {
            Map<ResourceLocation, Recipe<?>> copiedRecipes = new HashMap<>(entry.getValue());
            copiedRecipes.remove(recipeId);
            newRecipes.put(entry.getKey(), copiedRecipes);
        }

        recipesField.set(recipeManager, newRecipes);
    }
}