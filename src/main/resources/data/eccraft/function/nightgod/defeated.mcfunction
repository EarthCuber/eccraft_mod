# Nightgod defeated: executed as the boss at its current position.
schedule function eccraft:all/reset 2s append
advancement grant @a[distance=..48] only class:great_traval/nightgod
scoreboard players add #nightgod boss_time 1
tellraw @a {"text":"恭喜 ","color":"yellow","extra":[{"selector":"@a[distance=..48]"},{"text":" 成功击败 ","color":"yellow"},{"selector":"@s","color":"red"},{"text":" ！","color":"yellow"}]}