# SafeBackpack
A Slimefun based plugin which provides many backpacks which won't cause "Server stuck when opening guide"

Cause I don't want to edit the Slimefun plugin directly, I copied the codes from Slimefun and then modified them to fix the bug

## Description
Original Slimefun provides some backpacks and a cooler
But all of these items' inventories are stored in the same yaml file as player data, And when the players open up the slimefun guide, the Slimefun plugin will read the whole player yaml file(to get their research statuses) including their backpacks' inventories, if they have a plenty of items with complex nbt tags in their backpack inventories, it will even cause the dropping of the server TPS.

This plugin rewrite the Backpacks API and make every yaml file which stored the backpack inventory independent, so it will fix the problem of "Server stuck when a player opens the Slimefun guide"

## Items
Added 10 new items, they are "Safe Small Backpack", "Safe Backpack", "Safe Large Backpack", "Safe Woven Backpack", "Safe Gilded Backpack", "Safe Soulbound Backpack", "Safe Journey Backpack", "Safe Cooler", "Safe Reinforced Cooler" and "Canvas"

Among these items, "Safe Small Backpack", "Safe Backpack", "Safe Large Backpack", "Safe Woven Backpack", "Safe Gilded Backpack", "Safe Soulbound Backpack", and "Safe Cooler" are modified from the vanilla slimefun, their display names and lores look almost the same as the original backpacks from vanilla Slimefun, and them have the same functions as the original backpacks.

"Safe Journey Backpack" and "Safe Reinforced Cooler" both have 54 slots in their inventory, The former needs canvases and gilded iron ingots to craft, and the latter needs reinforced alloy ingots and canvases to craft. 

"Canvas" needs 16 Cloths to craft in the compressor

For more information, just open the game and try.

## Dyeing
The "Safe Small Backpack", "Safe Backpack", "Safe Large Backpack", "Safe Woven Backpack", "Safe Gilded Backpack", "Safe Journey Backpack" can be dyed with dyes and special permissions by Shift Right Clicking the air when holding the backpack

## Permissions
safebackpack.dye.* - Allows the player to use dye system (default: op)
safebackpack.dye.menu - Allows the player to open the dyeing menu
safebackpack.dye.white - Allows the player to dye the backpack into white
safebackpack.dye.orange - Allows the player to dye the backpack into orange
safebackpack.dye.magenta - Allows the player to dye the backpack into magenta
safebackpack.dye.light-blue - Allows the player to dye the backpack into light blue
safebackpack.dye.yellow - Allows the player to dye the backpack into yellow
safebackpack.dye.lime - Allows the player to dye the backpack into lime(light green)
safebackpack.dye.pink - Allows the player to dye the backpack into pink
safebackpack.dye.gray - Allows the player to dye the backpack into gray
safebackpack.dye.light-gray - Allows the player to dye the backpack into light gray
safebackpack.dye.cyan - Allows the player to dye the backpack into cyan
safebackpack.dye.purple - Allows the player to dye the backpack into purple
safebackpack.dye.blue - Allows the player to dye the backpack into blue
safebackpack.dye.brown - Allows the player to dye the backpack into brown
safebackpack.dye.green - Allows the player to dye the backpack into green
safebackpack.dye.red - Allows the player to dye the backpack into red
safebackpack.dye.black - Allows the player to dye the backpack into black

## Config
No configurations
