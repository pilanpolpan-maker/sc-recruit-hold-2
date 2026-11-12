package com.example.rsp;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/** ตรวจว่าไอเทมเป็นปืนของ Scorched Guns โดยไม่ต้อง compile-depend มอดนั้น */
public final class GunPose {
    private static final String NAMESPACE = "scguns";
    private static final String GUN_CLASS = "top.ribs.scguns.item.GunItem";

    private GunPose() {}

    public static boolean isScorchedGun(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return false;
        Item item = stack.getItem();
        if (!NAMESPACE.equals(BuiltInRegistries.ITEM.getKey(item).getNamespace())) return false;
        // เอาเฉพาะคลาสที่สืบจาก GunItem (กันกระสุน/attachment/ไอเทมอื่นของมอด)
        for (Class<?> c = item.getClass(); c != null; c = c.getSuperclass()) {
            if (GUN_CLASS.equals(c.getName())) return true;
        }
        return false;
    }
}
