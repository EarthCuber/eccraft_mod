# Runs once when a newly created Anubis begins its first server AI tick.
# Add commands below. In this function, @s is the newly spawned Anubis.
gamerule keep_inventory true
gamerule immediate_respawn true
tellraw @a[distance=..64] "巨龙降落，勇士们准备迎战！"