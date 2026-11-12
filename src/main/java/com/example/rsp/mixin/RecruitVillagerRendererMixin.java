package com.example.rsp.mixin;

import com.example.rsp.GunPose;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** getArmPose ของ Recruits เป็น private static -> handler ต้อง static */
@Mixin(targets = "com.talhanation.recruits.client.render.RecruitVillagerRenderer", remap = false)
public abstract class RecruitVillagerRendererMixin {

    @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true, remap = false)
    private static void rsp$scorchedGunPose(@Coerce LivingEntity entity, InteractionHand hand,
                                            CallbackInfoReturnable<HumanoidModel.ArmPose> cir) {
        if (GunPose.isScorchedGun(entity.getItemInHand(hand))) {
            cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
        }
    }
}
