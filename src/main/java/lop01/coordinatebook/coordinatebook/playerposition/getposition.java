package lop01.coordinatebook.coordinatebook.playerposition;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public class getposition {
        public static String getcoordinates() {
            // Get the Minecraft client instance
            MinecraftClient minecraft = MinecraftClient.getInstance();

            // Get the player entity
            PlayerEntity player = minecraft.player;

            var coordinates = "";

            // Get the player's position
            if (player != null) {
                BlockPos pos = player.getBlockPos();
                System.out.println("Player Position: X=" + pos.getX() + ", Y=" + pos.getY() + ", Z=" + pos.getZ());
                coordinates = pos.getX() + ", " + pos.getY() + ", " + pos.getZ();

                return coordinates;
            } else {
                return "error";
            }
        }
}

