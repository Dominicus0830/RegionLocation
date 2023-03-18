package com.dominicus0830.DORL.func;

import com.dominicus0830.DORL.RegionLocation;
import org.bukkit.Location;

public class DORLfunc {

    private RegionLocation plugin = RegionLocation.getInstance();
    public boolean isInsideDORL(Location location) {
        // 현재 위치의 x, y, z 좌표
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        // DORL 구역의 중심 좌표
        Location center = plugin.getCenter();
        // DORL 구역의 반지름
        int radius = plugin.getRadius();

        // DORL 구역 내에 있는지 확인
        if (x >= center.getBlockX() - radius && x <= center.getBlockX() + radius
                && y >= center.getBlockY() - radius && y <= center.getBlockY() + radius
                && z >= center.getBlockZ() - radius && z <= center.getBlockZ() + radius) {
            return true;
        }
        return false;
    }
}
