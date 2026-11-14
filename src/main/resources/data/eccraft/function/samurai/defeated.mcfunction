# Runs once at Anubis's position after its death animation finishes.
# Add commands below. In this function, @s is the defeated Anubis.
schedule function eccraft:all/reset 2s append
advancement grant @a[distance=..16] only class:great_traval/samurai
scoreboard players add #time samurai 1
tellraw @a {"text":"恭喜 ","color":"yellow","extra":[{"selector":"@a[distance=..24]"},{"text":" 成功击败 ","color":"yellow"},{"selector":"@s","color":"red"},{"text":" ！","color":"yellow"}]}