# Ice witch lifecycle hook; add commands here.
schedule function eccraft:all/reset 2s append
advancement grant @a[distance=..16] only class:great_traval/ice_witch
scoreboard players add #icewitch boss_time 1
tellraw @a {"text":"恭喜 ","color":"yellow","extra":[{"selector":"@a[distance=..24]"},{"text":" 成功击败 ","color":"yellow"},{"selector":"@s","color":"red"},{"text":" ！","color":"yellow"}]}