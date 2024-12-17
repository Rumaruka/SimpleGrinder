package com.rumaruka.simplegrinder.init;

import com.rumaruka.simplegrinder.common.items.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


import static com.rumaruka.simplegrinder.SimpleGrinder.MODID;
public class SGItems {
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredItem<DustItem> IRON_DUST = ITEMS.registerItem("dust_iron", DustItem::new,new Item.Properties());
    public static final DeferredItem<DustItem> GOLD_DUST = ITEMS.registerItem("dust_gold", DustItem::new,new Item.Properties());
    public static final DeferredItem<DustItem> COPPER_DUST = ITEMS.registerItem("dust_copper", DustItem::new,new Item.Properties());
    public static final DeferredItem<FlourItem> FLOUR = ITEMS.registerItem("flour", FlourItem::new,new Item.Properties());
    public static final DeferredItem<MashPotatoItem> MASH_POTATO = ITEMS.registerItem("mash_potato", MashPotatoItem::new,new Item.Properties().food(SGFood.MASH_POTATO, SGFood.MASH_POTATO_EFFECTS));
    public static final DeferredItem<MashCarrotItem> MASH_CARROT = ITEMS.registerItem("mash_carrot", MashCarrotItem::new,new Item.Properties().food(SGFood.MASH_CARROT, SGFood.MASH_CARROT_EFFECTS));
    public static final DeferredItem<OmleteItem> OMLETE = ITEMS.registerItem("omlete", OmleteItem::new,new Item.Properties().food(SGFood.OMLETE));
    public static final DeferredItem<BlockItem> GRINDERBLOCK_ITEM = ITEMS.registerSimpleBlockItem("coal_grinder", SGBlocks.COAL_GRINDER, new Item.Properties());
    public static final DeferredItem<BlockItem> MACHINE_CORE_ITEM = ITEMS.registerSimpleBlockItem("machine_core", SGBlocks.MACHINE_CORE, new Item.Properties());

    public static void setup(IEventBus bus) {
        ITEMS.register(bus);

    }


}
