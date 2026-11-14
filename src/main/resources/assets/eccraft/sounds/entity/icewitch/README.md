# 冰霜女巫音效

将单声道 OGG Vorbis 文件放在本目录：

| 用途 | 文件名 | 声音事件 |
|---|---|---|
| 技能 1 | icewitch_skill_1.ogg | eccraft:entity.icewitch.skill_1 |
| 技能 2 | icewitch_skill_2.ogg | eccraft:entity.icewitch.skill_2 |
| 技能 3 | icewitch_skill_3.ogg | eccraft:entity.icewitch.skill_3 |
| 死亡 | icewitch_death.ogg | eccraft:entity.icewitch.death |

普攻调用 minecraft:entity.player.hurt_freeze，无需提供文件。
普攻及三个技能在动作开始时播放一次；死亡通过原版死亡声音回调播放。
sounds.json 已完成声明，不需要再编辑它。当前没有创建虚假的空 OGG 文件。
