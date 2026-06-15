package net.nyataro.iceboundmod.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

import java.util.Properties;

public class ColdArmorItem extends ArmorItem {

    private final double coldResistance;

    public ColdArmorItem(ArmorMaterial material, Type type, Item.Properties properties, double coldResistance) {
        super(material, type, properties);
        this.coldResistance = coldResistance;
    }

    public double getColdResistance() {
        return coldResistance;
    }
}
