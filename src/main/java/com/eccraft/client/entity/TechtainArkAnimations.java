package com.eccraft.client.entity;
import net.minecraft.client.animation.*;
public final class TechtainArkAnimations {
public static final AnimationDefinition idle=build_idle();
private static AnimationDefinition build_idle(){var b=AnimationDefinition.Builder.withLength(3.8333F).looping();idle_0(b);idle_1(b);idle_2(b);idle_3(b);idle_4(b);return b.build();}
private static void idle_0(AnimationDefinition.Builder b){b.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7917F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.2083F, KeyframeAnimations.degreeVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(0.9974F, 0.3173F, -0.9483F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(0.9993F, -0.1587F, 0.4742F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(0.9974F, 0.3173F, -0.9483F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.875F, KeyframeAnimations.degreeVec(0.9993F, -0.1587F, 0.4742F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7917F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.2083F, KeyframeAnimations.degreeVec(-0.7612F, -0.459F, -1.9466F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(-0.7732F, 0.5285F, 2.2371F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(-0.7612F, -0.459F, -1.9466F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(-0.7732F, 0.5285F, 2.2371F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("yanpi", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.3F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("yanpi2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("lhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7917F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.2083F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(-2.6805F, 0.3843F, 0.9235F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(-2.4694F, -0.3813F, -0.925F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(-2.6805F, 0.3843F, 0.9235F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.875F, KeyframeAnimations.degreeVec(-2.4694F, -0.3813F, -0.925F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("lhand", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7917F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.2083F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void idle_1(AnimationDefinition.Builder b){b.addAnimation("angry", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("shock", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("close", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-65.368F, -85.0914F, 69.4151F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-98.8842F, -43.1389F, 95.4885F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-99.7736F, -32.2351F, 102.8669F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-96.182F, -21.3222F, 113.3777F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-179.6504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-164.7451F, 19.0602F, 198.9403F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-98.7921F, 40.3432F, 264.1216F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-154.6357F, 86.9786F, 200.4091F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-282.6345F, 27.6039F, 79.6914F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-350.8496F, -1.8962F, -4.2262F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-272.8958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(-269.2719F, -29.1139F, -79.9636F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(-283.8457F, -48.0609F, -82.8255F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(-98.8842F, -43.1389F, 95.4885F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.degreeVec(-99.7736F, -32.2351F, 102.8669F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(-96.182F, -21.3222F, 113.3777F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(-179.6504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.degreeVec(-164.7451F, 19.0602F, 198.9403F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(-98.7921F, 40.3432F, 264.1216F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.degreeVec(-164.7451F, 19.0602F, 198.9403F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(-179.6504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.degreeVec(-96.182F, -21.3222F, 113.3777F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.degreeVec(-99.7736F, -32.2351F, 102.8669F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(-164.7451F, 19.0602F, 198.9403F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(-96.6356F, 30.102F, 247.3809F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(-98.7921F, 40.3432F, 264.1216F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.2083F, KeyframeAnimations.degreeVec(-282.6345F, 27.6039F, 79.6914F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.25F, KeyframeAnimations.degreeVec(-278.7219F, 20.9325F, 65.1285F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.degreeVec(-350.8496F, -1.8962F, -4.2262F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(-272.8958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(-269.2719F, -29.1139F, -79.9636F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.degreeVec(-283.8457F, -48.0609F, -82.8255F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(31.1F, 25.9F, 28.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(30.655F, 18.93F, 42.345F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(29.32F, 13.095F, 47.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(27.295F, 6.075F, 50.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(10.795F, 0.355F, 52.005F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-3.13F, 0.44F, 51.63F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-12.9F, 17.605F, 43.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(-13.43F, 25.82F, 26.41F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(-12.23F, 13.945F, 5.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(16.125F, -0.14F, -0.465F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(27.92F, 8.025F, 1.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(30.4F, 14.09F, 5.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(30.065F, 18.675F, 7.84F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(30.655F, 18.93F, 42.345F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.posVec(29.32F, 13.095F, 47.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.posVec(27.295F, 6.075F, 50.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(10.795F, 0.355F, 52.005F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.posVec(-3.13F, 0.44F, 51.63F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.posVec(-12.9F, 17.605F, 43.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.posVec(-3.13F, 0.44F, 51.63F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.posVec(10.795F, 0.355F, 52.005F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.posVec(27.295F, 6.075F, 50.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.posVec(29.32F, 13.095F, 47.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.posVec(-3.13F, 0.44F, 51.63F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0833F, KeyframeAnimations.posVec(-11.43F, 10.04F, 48.93F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(-12.9F, 17.605F, 43.61F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.2083F, KeyframeAnimations.posVec(-12.23F, 13.945F, 5.56F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.25F, KeyframeAnimations.posVec(-10.565F, 7.76F, 1.89F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.posVec(16.125F, -0.14F, -0.465F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(27.92F, 8.025F, 1.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.posVec(30.4F, 14.09F, 5.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.posVec(30.065F, 18.675F, 7.84F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-85.8504F, -60.4717F, 90.3553F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-102.1107F, -29.397F, 102.3709F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-113.3936F, -27.5113F, 118.085F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-159.5618F, -3.7147F, 163.529F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-172.1504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-99.5273F, 20.7275F, 241.2514F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-95.5207F, 59.9378F, 269.6097F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-263.2361F, 66.1476F, 91.2049F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-285.094F, 22.0422F, 73.2632F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-289.3974F, -33.3149F, -49.3241F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-280.3958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(-267.9874F, -48.5394F, -91.4801F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(-288.6993F, -60.0746F, -76.866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(-102.1107F, -29.397F, 102.3709F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.degreeVec(-113.3936F, -27.5113F, 118.085F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(-159.5618F, -3.7147F, 163.529F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(-172.1504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.degreeVec(-99.5273F, 20.7275F, 241.2514F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(-95.5207F, 59.9378F, 269.6097F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.degreeVec(-99.5273F, 20.7275F, 241.2514F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(-172.1504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.degreeVec(-159.5618F, -3.7147F, 163.529F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.degreeVec(-113.3936F, -27.5113F, 118.085F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(-99.5273F, 20.7275F, 241.2514F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(-91.3367F, 40.6952F, 263.3294F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(-95.5207F, 59.9378F, 269.6097F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.2083F, KeyframeAnimations.degreeVec(-285.094F, 22.0422F, 73.2632F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.degreeVec(-289.3974F, -33.3149F, -49.3241F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(-280.3958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(-267.9874F, -48.5394F, -91.4801F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.degreeVec(-288.6993F, -60.0746F, -76.866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void idle_2(AnimationDefinition.Builder b){b.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(22.3F, 23.7F, 35.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(20.955F, 13.63F, 47.645F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(18.22F, 6.095F, 49.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(10.67F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(-5.505F, 0.43F, 52.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-18.18F, 5.62F, 51.18F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-22.0F, 24.005F, 36.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(-22.23F, 25.02F, 18.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(-18.03F, 5.345F, 1.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(16.125F, 1.61F, -0.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(21.295F, 14.425F, 5.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(22.1F, 20.64F, 10.86F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(22.065F, 23.475F, 14.24F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(20.955F, 13.63F, 47.645F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.posVec(18.22F, 6.095F, 49.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.posVec(10.67F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(-5.505F, 0.43F, 52.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.posVec(-18.18F, 5.62F, 51.18F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.posVec(-22.0F, 24.005F, 36.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.posVec(-18.18F, 5.62F, 51.18F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.posVec(-5.505F, 0.43F, 52.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.posVec(10.67F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.posVec(18.22F, 6.095F, 49.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.posVec(-18.18F, 5.62F, 51.18F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0833F, KeyframeAnimations.posVec(-21.83F, 18.62F, 44.08F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(-22.0F, 24.005F, 36.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.2083F, KeyframeAnimations.posVec(-18.03F, 5.345F, 1.06F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.25F, KeyframeAnimations.posVec(-11.185F, 0.465F, -1.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.posVec(16.125F, 1.61F, -0.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(21.295F, 14.425F, 5.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.posVec(22.1F, 20.64F, 10.86F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.posVec(22.065F, 23.475F, 14.24F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-93.8583F, -74.9675F, 93.7266F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-86.6424F, -52.4887F, 91.303F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-105.8789F, -20.9109F, 112.5468F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-166.9536F, 7.9768F, 181.4866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-132.8382F, 37.2902F, 230.8687F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-95.6712F, 16.3924F, 246.8258F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-181.1006F, 87.2371F, 183.2895F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-265.0888F, 56.1979F, 89.0981F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-343.6514F, 15.7568F, 12.7989F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-279.8191F, -36.8748F, -65.8983F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-266.4444F, -45.0479F, -93.4194F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(-263.3136F, -78.4806F, -96.5246F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(-259.6189F, -80.5013F, -98.0023F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(-398.3715F, -78.349F, 37.0455F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-398.3715F, -78.349F, 37.0455F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.degreeVec(-398.3715F, -78.349F, 37.0455F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(-105.8789F, -20.9109F, 112.5468F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.degreeVec(-105.8789F, -20.9109F, 112.5468F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(-166.9536F, 7.9768F, 181.4866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(-132.8382F, 37.2902F, 230.8687F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.degreeVec(-95.6712F, 16.3924F, 246.8258F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(-181.1006F, 87.2371F, 183.2895F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.degreeVec(-95.6712F, 16.3924F, 246.8258F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(-132.8382F, 37.2902F, 230.8687F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.degreeVec(-166.9536F, 7.9768F, 181.4866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.degreeVec(-134.5927F, -32.6396F, 156.6418F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(-95.6712F, 16.3924F, 246.8258F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(-93.0575F, 57.2062F, 258.3234F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(-181.1006F, 87.2371F, 183.2895F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.2083F, KeyframeAnimations.degreeVec(-343.6514F, 15.7568F, 12.7989F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.degreeVec(-279.8191F, -36.8748F, -65.8983F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(-266.4444F, -45.0479F, -93.4194F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(-263.3136F, -78.4806F, -96.5246F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.degreeVec(-259.6189F, -80.5013F, -98.0023F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.degreeVec(-398.3715F, -78.349F, 37.0455F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(13.8F, 20.0F, 42.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(10.58F, 7.13F, 49.67F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(1.52F, 1.27F, 52.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(-5.93F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(-22.005F, 1.13F, 52.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-29.61F, 12.62F, 47.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-30.5F, 26.105F, 26.01F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(-30.63F, 20.82F, 11.51F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(-16.68F, 0.045F, -1.09F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(11.225F, 9.11F, 1.81F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(13.095F, 20.825F, 10.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(13.4F, 24.74F, 18.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(10.58F, 7.13F, 49.67F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.posVec(1.52F, 1.27F, 52.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.posVec(-5.93F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(-22.005F, 1.13F, 52.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.posVec(-29.61F, 12.62F, 47.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.posVec(-30.5F, 26.105F, 26.01F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.posVec(-29.61F, 12.62F, 47.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.posVec(-22.005F, 1.13F, 52.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.posVec(-5.93F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.posVec(3.32F, 1.27F, 52.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.posVec(-29.61F, 12.62F, 47.96F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0833F, KeyframeAnimations.posVec(-31.01F, 24.92F, 34.36F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(-30.5F, 26.105F, 26.01F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.2083F, KeyframeAnimations.posVec(-16.68F, 0.045F, -1.09F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.25F, KeyframeAnimations.posVec(-9.13F, 0.25F, 0.39F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.posVec(11.225F, 9.11F, 1.81F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(13.095F, 20.825F, 10.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.posVec(13.4F, 24.74F, 18.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(87.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(104.3033F, 32.2362F, -246.8753F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(255.5873F, 81.4735F, -95.7486F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(267.3615F, 36.7089F, -83.0666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(362.4224F, 6.9398F, -0.8472F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(284.0933F, -81.2714F, 81.9731F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(445.2676F, -63.3915F, -79.8573F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(447.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(447.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.degreeVec(447.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9167F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(104.3033F, 32.2362F, -246.8753F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.degreeVec(255.5873F, 81.4735F, -95.7486F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(267.3615F, 36.7089F, -83.0666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.degreeVec(255.5873F, 81.4735F, -95.7486F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.degreeVec(104.3033F, 32.2362F, -246.8753F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.875F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.degreeVec(104.3033F, 32.2362F, -246.8753F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(255.5873F, 81.4735F, -95.7486F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(267.3615F, 36.7089F, -83.0666F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.2083F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(362.4224F, 6.9398F, -0.8472F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(284.0933F, -81.2714F, 81.9731F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.degreeVec(445.2676F, -63.3915F, -79.8573F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.degreeVec(447.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-6.9F, 1.8F, -54.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(4.4F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(19.3F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(26.4F, 7.0F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(30.4F, 19.9F, -42.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(30.8F, 26.4F, -26.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(30.8F, 18.7F, -9.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(26.1F, 4.2F, -1.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-11.7F, 11.7F, -3.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(-13.1F, 25.3F, -20.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(-13.6F, 22.9F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(4.4F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.posVec(19.3F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.posVec(26.4F, 7.0F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(30.4F, 19.9F, -42.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.posVec(30.8F, 26.4F, -26.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.posVec(30.8F, 18.7F, -9.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.posVec(30.8F, 26.4F, -26.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.posVec(30.4F, 19.9F, -42.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.posVec(26.4F, 7.0F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.875F, KeyframeAnimations.posVec(19.3F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.posVec(26.4F, 7.0F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.posVec(30.4F, 19.9F, -42.6F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0833F, KeyframeAnimations.posVec(30.8F, 26.4F, -26.6F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(30.8F, 18.7F, -9.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.2083F, KeyframeAnimations.posVec(26.1F, 4.2F, -1.1F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.4167F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(-11.7F, 11.7F, -3.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.posVec(-13.1F, 25.3F, -20.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.posVec(-13.6F, 22.9F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("bone23", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void idle_3(AnimationDefinition.Builder b){b.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(86.6072F, -45.1048F, -100.1054F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(93.439F, -29.9049F, -107.813F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(93.5006F, -41.8759F, -111.9761F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(177.5572F, -5.9959F, -197.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(102.3852F, 30.2628F, -239.017F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(95.8788F, 39.0284F, -264.8805F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(97.3809F, 88.5476F, -264.539F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(273.0528F, 42.9326F, -81.7794F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(280.8679F, 37.7136F, -69.9546F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(355.4252F, -2.9184F, 6.5199F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(273.719F, -19.6137F, 80.6991F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(268.4388F, -61.5322F, 98.1823F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9167F, KeyframeAnimations.degreeVec(93.439F, -29.9049F, -107.813F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(93.5006F, -41.8759F, -111.9761F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(177.5572F, -5.9959F, -197.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(102.3852F, 30.2628F, -239.017F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.degreeVec(95.8788F, 39.0284F, -264.8805F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(97.3809F, 88.5476F, -264.539F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.degreeVec(95.8788F, 39.0284F, -264.8805F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(102.3852F, 30.2628F, -239.017F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.degreeVec(177.5572F, -5.9959F, -197.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.875F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.degreeVec(177.5572F, -5.9959F, -197.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(102.3852F, 30.2628F, -239.017F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(95.8788F, 39.0284F, -264.8805F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(97.3809F, 88.5476F, -264.539F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.2083F, KeyframeAnimations.degreeVec(273.0528F, 42.9326F, -81.7794F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(280.8679F, 37.7136F, -69.9546F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(355.4252F, -2.9184F, 6.5199F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(273.719F, -19.6137F, 80.6991F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.degreeVec(268.4388F, -61.5322F, 98.1823F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.degreeVec(452.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-30.81F, 14.6F, -46.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(-29.0F, 7.5F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(-16.6F, -0.1F, -52.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(-5.1F, 0.4F, -52.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(9.2F, 5.1F, -51.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(12.7F, 16.9F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(13.4F, 26.5F, -24.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(13.25F, 17.4F, -9.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(10.06F, 6.69F, -3.16F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-18.8F, -0.1F, -0.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(-29.95F, 13.9F, -5.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(-30.2F, 24.9F, -19.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(-29.0F, 7.5F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.posVec(-16.6F, -0.1F, -52.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.posVec(-5.1F, 0.4F, -52.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(9.2F, 5.1F, -51.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.posVec(12.7F, 16.9F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.posVec(13.4F, 26.5F, -24.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.posVec(12.7F, 16.9F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.posVec(9.2F, 5.1F, -51.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.posVec(-5.1F, 0.4F, -52.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.875F, KeyframeAnimations.posVec(-16.6F, -0.1F, -52.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.posVec(-5.1F, 0.4F, -52.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.posVec(9.2F, 5.1F, -51.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0833F, KeyframeAnimations.posVec(12.7F, 16.9F, -45.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(13.4F, 26.5F, -24.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.2083F, KeyframeAnimations.posVec(13.25F, 17.4F, -9.1F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.3333F, KeyframeAnimations.posVec(10.06F, 6.69F, -3.16F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(-18.8F, -0.1F, -0.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.posVec(-29.95F, 13.9F, -5.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.posVec(-30.2F, 24.9F, -19.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("bone24", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(181.0207F, -21.4331F, -170.8872F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(161.0027F, 14.8667F, -204.743F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(103.895F, 70.6504F, -255.4522F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(265.5404F, 61.6655F, -85.4158F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(308.2499F, 31.2143F, -27.8326F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(273.0766F, -46.489F, 93.6773F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(442.5951F, -73.3384F, -76.9932F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9167F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(181.0207F, -21.4331F, -170.8872F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(161.0027F, 14.8667F, -204.743F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.degreeVec(103.895F, 70.6504F, -255.4522F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(265.5404F, 61.6655F, -85.4158F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.degreeVec(103.895F, 70.6504F, -255.4522F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.degreeVec(161.0027F, 14.8667F, -204.743F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.875F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.degreeVec(161.0027F, 14.8667F, -204.743F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(103.895F, 70.6504F, -255.4522F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(265.5404F, 61.6655F, -85.4158F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.2083F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(308.2499F, 31.2143F, -27.8326F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(273.0766F, -46.489F, 93.6773F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.degreeVec(442.5951F, -73.3384F, -76.9932F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.degreeVec(454.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-18.51F, 7.3F, -51.07F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(-13.6F, -0.1F, -50.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(1.7F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(11.5F, 0.4F, -53.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(20.4F, 12.3F, -48.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(22.0F, 22.6F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(22.2F, 23.8F, -15.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(20.4F, 11.1F, -3.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(13.46F, 0.99F, 0.44F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-17.2F, 4.2F, -1.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(-21.7F, 20.5F, -11.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(-21.9F, 26.6F, -28.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(-13.6F, -0.1F, -50.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.posVec(1.7F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.posVec(11.5F, 0.4F, -53.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(20.4F, 12.3F, -48.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.posVec(22.0F, 22.6F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.posVec(22.2F, 23.8F, -15.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.posVec(22.0F, 22.6F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.posVec(20.4F, 12.3F, -48.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.posVec(11.5F, 0.4F, -53.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.875F, KeyframeAnimations.posVec(1.7F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.posVec(11.5F, 0.4F, -53.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.posVec(20.4F, 12.3F, -48.1F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0833F, KeyframeAnimations.posVec(22.0F, 22.6F, -37.1F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(22.2F, 23.8F, -15.8F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.2083F, KeyframeAnimations.posVec(20.4F, 11.1F, -3.6F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.3333F, KeyframeAnimations.posVec(13.46F, 0.99F, 0.44F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(-17.2F, 4.2F, -1.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.posVec(-21.7F, 20.5F, -11.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.posVec(-21.9F, 26.6F, -28.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("bone25", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void idle_4(AnimationDefinition.Builder b){b.addAnimation("rhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7917F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.2083F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(-2.4694F, 0.3813F, 0.925F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(-2.5245F, -0.3818F, -0.9246F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(-2.4694F, 0.3813F, 0.925F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.875F, KeyframeAnimations.degreeVec(-2.5245F, -0.3818F, -0.9246F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("rhand", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7917F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.2083F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("horn2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("horn1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(22.3059F, 0.9494F, -2.3128F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(19.9577F, -0.8023F, 2.4947F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.degreeVec(22.3059F, 0.9494F, -2.3128F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(19.9577F, -0.8023F, 2.4947F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(22.3059F, 0.9494F, -2.3128F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.degreeVec(19.9577F, -0.8023F, 2.4947F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
public static final AnimationDefinition attack=build_attack();
private static AnimationDefinition build_attack(){var b=AnimationDefinition.Builder.withLength(1.7083F);attack_0(b);attack_1(b);attack_2(b);attack_3(b);attack_4(b);attack_5(b);attack_6(b);attack_7(b);return b.build();}
private static void attack_0(AnimationDefinition.Builder b){b.addAnimation("main", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-12.6866F, 9.7606F, -2.1856F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.degreeVec(-12.6866F, -360.7606F, -2.1856F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-19.5952F, -409.0961F, 12.7026F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(-19.5952F, -409.0961F, 12.7026F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("wheel2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(19.0484F, 27.6469F, -2.7221F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(19.0484F, 27.6469F, -2.7221F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("wheel2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2917F, KeyframeAnimations.degreeVec(-9.7023F, 37.8881F, 8.6588F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.4583F, KeyframeAnimations.degreeVec(-9.7023F, 37.8881F, 8.6588F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5833F, KeyframeAnimations.degreeVec(-7.8609F, 50.2464F, 11.2907F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(14.6391F, 50.2464F, 11.2907F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(6.132F, 53.7678F, 0.5134F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.375F, KeyframeAnimations.posVec(-1.3F, 0.0F, -3.8F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(-1.3F, 0.0F, -3.8F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.4167F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void attack_1(AnimationDefinition.Builder b){b.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.4167F, KeyframeAnimations.posVec(0.0F, -1.3F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("yanpi", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(0.0F, 0.3F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("yanpi2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(0.0F, -0.6F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("lhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(14.0243F, -0.7906F, -7.4633F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-19.5807F, -33.4348F, -18.4793F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-19.5807F, -33.4348F, -18.4793F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(21.8479F, -3.4215F, -21.2865F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.degreeVec(19.4661F, 6.4456F, -42.0712F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(18.9148F, 8.7591F, -18.5955F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("lhand", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.375F, KeyframeAnimations.posVec(0.0F, 0.2F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.2F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.2917F, KeyframeAnimations.posVec(0.0F, 0.2F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void attack_2(AnimationDefinition.Builder b){b.addAnimation("hand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(-7.0F, 0.0F, 21.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-10.121F, -0.9669F, 14.0259F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(-11.8357F, -1.5115F, 8.5903F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("finger1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void attack_3(AnimationDefinition.Builder b){b.addAnimation("toe3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("mild", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("shock", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("close", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void attack_4(AnimationDefinition.Builder b){b.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-65.368F, -85.0914F, 69.4151F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-98.8842F, -43.1389F, 95.4885F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-99.7736F, -32.2351F, 102.8669F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-96.182F, -21.3222F, 113.3777F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-179.6504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-164.7451F, 19.0602F, 198.9403F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-98.7921F, 40.3432F, 264.1216F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-154.6357F, 86.9786F, 200.4091F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-282.6345F, 27.6039F, 79.6914F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-350.8496F, -1.8962F, -4.2262F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-272.8958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(-269.2719F, -29.1139F, -79.9636F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(-283.8457F, -48.0609F, -82.8255F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(31.1F, 25.9F, 28.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(30.655F, 18.93F, 42.345F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(29.32F, 13.095F, 47.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(27.295F, 6.075F, 50.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(10.795F, 0.355F, 52.005F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-3.13F, 0.44F, 51.63F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-12.9F, 17.605F, 43.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(-13.43F, 25.82F, 26.41F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(-12.23F, 13.945F, 5.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(16.125F, -0.14F, -0.465F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(27.92F, 8.025F, 1.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(30.4F, 14.09F, 5.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(30.065F, 18.675F, 7.84F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-85.8504F, -60.4717F, 90.3553F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-102.1107F, -29.397F, 102.3709F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-113.3936F, -27.5113F, 118.085F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-159.5618F, -3.7147F, 163.529F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-172.1504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-99.5273F, 20.7275F, 241.2514F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-95.5207F, 59.9378F, 269.6097F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-263.2361F, 66.1476F, 91.2049F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-285.094F, 22.0422F, 73.2632F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-289.3974F, -33.3149F, -49.3241F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-280.3958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(-267.9874F, -48.5394F, -91.4801F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(-288.6993F, -60.0746F, -76.866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(22.3F, 23.7F, 35.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(20.955F, 13.63F, 47.645F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(18.22F, 6.095F, 49.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(10.67F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(-5.505F, 0.43F, 52.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-18.18F, 5.62F, 51.18F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-22.0F, 24.005F, 36.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(-22.23F, 25.02F, 18.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(-18.03F, 5.345F, 1.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(16.125F, 1.61F, -0.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(21.295F, 14.425F, 5.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(22.1F, 20.64F, 10.86F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(22.065F, 23.475F, 14.24F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-93.8583F, -74.9675F, 93.7266F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-86.6424F, -52.4887F, 91.303F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-105.8789F, -20.9109F, 112.5468F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-166.9536F, 7.9768F, 181.4866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-132.8382F, 37.2902F, 230.8687F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-95.6712F, 16.3924F, 246.8258F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-181.1006F, 87.2371F, 183.2895F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-265.0888F, 56.1979F, 89.0981F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-343.6514F, 15.7568F, 12.7989F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-279.8191F, -36.8748F, -65.8983F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-266.4444F, -45.0479F, -93.4194F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(-263.3136F, -78.4806F, -96.5246F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(-259.6189F, -80.5013F, -98.0023F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(-398.3715F, -78.349F, 37.0455F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(13.8F, 20.0F, 42.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(10.58F, 7.13F, 49.67F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(1.52F, 1.27F, 52.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(-5.93F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(-22.005F, 1.13F, 52.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-29.61F, 12.62F, 47.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-30.5F, 26.105F, 26.01F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(-30.63F, 20.82F, 11.51F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(-16.68F, 0.045F, -1.09F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(11.225F, 9.11F, 1.81F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(13.095F, 20.825F, 10.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(13.4F, 24.74F, 18.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void attack_5(AnimationDefinition.Builder b){b.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(87.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(104.3033F, 32.2362F, -246.8753F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(255.5873F, 81.4735F, -95.7486F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(267.3615F, 36.7089F, -83.0666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(362.4224F, 6.9398F, -0.8472F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(284.0933F, -81.2714F, 81.9731F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(445.2676F, -63.3915F, -79.8573F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(447.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-6.9F, 1.8F, -54.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(4.4F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(19.3F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(26.4F, 7.0F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(30.4F, 19.9F, -42.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(30.8F, 26.4F, -26.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(30.8F, 18.7F, -9.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(26.1F, 4.2F, -1.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-11.7F, 11.7F, -3.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(-13.1F, 25.3F, -20.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(-13.6F, 22.9F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(86.6072F, -45.1048F, -100.1054F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(93.439F, -29.9049F, -107.813F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(93.5006F, -41.8759F, -111.9761F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(177.5572F, -5.9959F, -197.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(102.3852F, 30.2628F, -239.017F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(95.8788F, 39.0284F, -264.8805F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(97.3809F, 88.5476F, -264.539F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(273.0528F, 42.9326F, -81.7794F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(280.8679F, 37.7136F, -69.9546F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(355.4252F, -2.9184F, 6.5199F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(273.719F, -19.6137F, 80.6991F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(268.4388F, -61.5322F, 98.1823F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-30.81F, 14.6F, -46.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(-29.0F, 7.5F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(-16.6F, -0.1F, -52.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(-5.1F, 0.4F, -52.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(9.2F, 5.1F, -51.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(12.7F, 16.9F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(13.4F, 26.5F, -24.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(13.25F, 17.4F, -9.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(10.06F, 6.69F, -3.16F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-18.8F, -0.1F, -0.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(-29.95F, 13.9F, -5.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(-30.2F, 24.9F, -19.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(181.0207F, -21.4331F, -170.8872F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(161.0027F, 14.8667F, -204.743F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(103.895F, 70.6504F, -255.4522F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(265.5404F, 61.6655F, -85.4158F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(308.2499F, 31.2143F, -27.8326F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(273.0766F, -46.489F, 93.6773F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(442.5951F, -73.3384F, -76.9932F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-18.51F, 7.3F, -51.07F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(-13.6F, -0.1F, -50.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(1.7F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(11.5F, 0.4F, -53.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(20.4F, 12.3F, -48.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(22.0F, 22.6F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(22.2F, 23.8F, -15.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(20.4F, 11.1F, -3.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(13.46F, 0.99F, 0.44F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-17.2F, 4.2F, -1.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(-21.7F, 20.5F, -11.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(-21.9F, 26.6F, -28.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void attack_6(AnimationDefinition.Builder b){b.addAnimation("rhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(-92.1541F, 48.1876F, -45.1899F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(-49.1281F, 2.6877F, -29.0485F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.degreeVec(-49.1281F, 2.6877F, -29.0485F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(-24.4454F, -0.688F, -35.7543F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(-23.2161F, 6.6671F, 4.0651F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("rhand", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(-2.9F, 5.0F, 13.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.posVec(-1.5F, 2.8F, -1.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.posVec(-1.5F, 2.8F, -1.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.posVec(-1.1F, -0.6F, -1.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(-1.1F, 1.4F, -1.1F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("hand2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 42.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 42.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(32.5F, 0.0F, 42.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(25.1593F, 21.2842F, 4.8022F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("hand2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(1.1667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void attack_7(AnimationDefinition.Builder b){b.addAnimation("finger6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(42.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("finger7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("finger8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
public static final AnimationDefinition move=build_move();
private static AnimationDefinition build_move(){var b=AnimationDefinition.Builder.withLength(1.75F).looping();move_0(b);move_1(b);move_2(b);move_3(b);move_4(b);move_5(b);move_6(b);move_7(b);return b.build();}
private static void move_0(AnimationDefinition.Builder b){b.addAnimation("wheel2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("wheel", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(1435.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("wheel", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5417F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 2.8F, -2.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5417F, KeyframeAnimations.posVec(0.0F, 2.8F, -2.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(0.0F, 2.8F, -2.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(0.0F, 2.8F, -2.8F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5417F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void move_1(AnimationDefinition.Builder b){b.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("lhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.874F, -14.3495F, -21.9855F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("lhand", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(2.4F, 1.8F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("hand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(29.6217F, 4.9809F, 16.3178F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(82.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.4583F, KeyframeAnimations.degreeVec(77.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(82.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(80.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void move_2(AnimationDefinition.Builder b){b.addAnimation("finger2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(42.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5417F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(42.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(67.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void move_3(AnimationDefinition.Builder b){b.addAnimation("angry", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("shock", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("close", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-65.368F, -85.0914F, 69.4151F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-98.8842F, -43.1389F, 95.4885F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-99.7736F, -32.2351F, 102.8669F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-96.182F, -21.3222F, 113.3777F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-179.6504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-164.7451F, 19.0602F, 198.9403F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-98.7921F, 40.3432F, 264.1216F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-154.6357F, 86.9786F, 200.4091F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-282.6345F, 27.6039F, 79.6914F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-350.8496F, -1.8962F, -4.2262F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-272.8958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(-269.2719F, -29.1139F, -79.9636F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(-283.8457F, -48.0609F, -82.8255F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(31.1F, 25.9F, 28.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(30.655F, 18.93F, 42.345F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(29.32F, 13.095F, 47.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(27.295F, 6.075F, 50.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(10.795F, 0.355F, 52.005F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-3.13F, 0.44F, 51.63F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-12.9F, 17.605F, 43.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(-13.43F, 25.82F, 26.41F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(-12.23F, 13.945F, 5.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(16.125F, -0.14F, -0.465F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(27.92F, 8.025F, 1.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(30.4F, 14.09F, 5.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(30.065F, 18.675F, 7.84F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-85.8504F, -60.4717F, 90.3553F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-102.1107F, -29.397F, 102.3709F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-113.3936F, -27.5113F, 118.085F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-159.5618F, -3.7147F, 163.529F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-172.1504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-99.5273F, 20.7275F, 241.2514F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-95.5207F, 59.9378F, 269.6097F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-263.2361F, 66.1476F, 91.2049F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-285.094F, 22.0422F, 73.2632F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-289.3974F, -33.3149F, -49.3241F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-280.3958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(-267.9874F, -48.5394F, -91.4801F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(-288.6993F, -60.0746F, -76.866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void move_4(AnimationDefinition.Builder b){b.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(22.3F, 23.7F, 35.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(20.955F, 13.63F, 47.645F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(18.22F, 6.095F, 49.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(10.67F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(-5.505F, 0.43F, 52.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-18.18F, 5.62F, 51.18F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-22.0F, 24.005F, 36.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(-22.23F, 25.02F, 18.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(-18.03F, 5.345F, 1.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(16.125F, 1.61F, -0.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(21.295F, 14.425F, 5.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(22.1F, 20.64F, 10.86F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(22.065F, 23.475F, 14.24F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-93.8583F, -74.9675F, 93.7266F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-86.6424F, -52.4887F, 91.303F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-105.8789F, -20.9109F, 112.5468F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-166.9536F, 7.9768F, 181.4866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-132.8382F, 37.2902F, 230.8687F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-95.6712F, 16.3924F, 246.8258F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-181.1006F, 87.2371F, 183.2895F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-265.0888F, 56.1979F, 89.0981F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-343.6514F, 15.7568F, 12.7989F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-279.8191F, -36.8748F, -65.8983F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-266.4444F, -45.0479F, -93.4194F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(-263.3136F, -78.4806F, -96.5246F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(-259.6189F, -80.5013F, -98.0023F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(-398.3715F, -78.349F, 37.0455F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-398.3715F, -78.349F, 37.0455F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(13.8F, 20.0F, 42.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(10.58F, 7.13F, 49.67F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(1.52F, 1.27F, 52.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(-5.93F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(-22.005F, 1.13F, 52.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-29.61F, 12.62F, 47.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-30.5F, 26.105F, 26.01F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(-30.63F, 20.82F, 11.51F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(-16.68F, 0.045F, -1.09F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(11.225F, 9.11F, 1.81F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(13.095F, 20.825F, 10.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(13.4F, 24.74F, 18.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(87.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(104.3033F, 32.2362F, -246.8753F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(255.5873F, 81.4735F, -95.7486F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(267.3615F, 36.7089F, -83.0666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(362.4224F, 6.9398F, -0.8472F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(284.0933F, -81.2714F, 81.9731F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(445.2676F, -63.3915F, -79.8573F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(447.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(447.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-6.9F, 1.8F, -54.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(4.4F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(19.3F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(26.4F, 7.0F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(30.4F, 19.9F, -42.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(30.8F, 26.4F, -26.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(30.8F, 18.7F, -9.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(26.1F, 4.2F, -1.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-11.7F, 11.7F, -3.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(-13.1F, 25.3F, -20.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(-13.6F, 22.9F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("bone23", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void move_5(AnimationDefinition.Builder b){b.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(86.6072F, -45.1048F, -100.1054F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(93.439F, -29.9049F, -107.813F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(93.5006F, -41.8759F, -111.9761F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(177.5572F, -5.9959F, -197.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(102.3852F, 30.2628F, -239.017F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(95.8788F, 39.0284F, -264.8805F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(97.3809F, 88.5476F, -264.539F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(273.0528F, 42.9326F, -81.7794F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(280.8679F, 37.7136F, -69.9546F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(355.4252F, -2.9184F, 6.5199F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(273.719F, -19.6137F, 80.6991F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(268.4388F, -61.5322F, 98.1823F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-30.81F, 14.6F, -46.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(-29.0F, 7.5F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(-16.6F, -0.1F, -52.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(-5.1F, 0.4F, -52.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(9.2F, 5.1F, -51.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(12.7F, 16.9F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(13.4F, 26.5F, -24.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(13.25F, 17.4F, -9.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(10.06F, 6.69F, -3.16F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-18.8F, -0.1F, -0.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(-29.95F, 13.9F, -5.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(-30.2F, 24.9F, -19.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("bone24", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(181.0207F, -21.4331F, -170.8872F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(161.0027F, 14.8667F, -204.743F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(103.895F, 70.6504F, -255.4522F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(265.5404F, 61.6655F, -85.4158F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(308.2499F, 31.2143F, -27.8326F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(273.0766F, -46.489F, 93.6773F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(442.5951F, -73.3384F, -76.9932F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-18.51F, 7.3F, -51.07F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(-13.6F, -0.1F, -50.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(1.7F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(11.5F, 0.4F, -53.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(20.4F, 12.3F, -48.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(22.0F, 22.6F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(22.2F, 23.8F, -15.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(20.4F, 11.1F, -3.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(13.46F, 0.99F, 0.44F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-17.2F, 4.2F, -1.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(-21.7F, 20.5F, -11.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(-21.9F, 26.6F, -28.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("bone25", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void move_6(AnimationDefinition.Builder b){b.addAnimation("rhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(21.8291F, 1.7653F, 29.7138F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("hand2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -22.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.4583F, KeyframeAnimations.degreeVec(42.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(97.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5417F, KeyframeAnimations.degreeVec(62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void move_7(AnimationDefinition.Builder b){b.addAnimation("finger7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(42.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(42.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(77.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(72.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
public static final AnimationDefinition skill1=build_skill1();
private static AnimationDefinition build_skill1(){var b=AnimationDefinition.Builder.withLength(6.0F);skill1_0(b);skill1_1(b);skill1_2(b);skill1_3(b);skill1_4(b);skill1_5(b);skill1_6(b);skill1_7(b);return b.build();}
private static void skill1_0(AnimationDefinition.Builder b){b.addAnimation("main", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-17.5F, 0.0F, 4.7656F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-17.5F, 0.0F, 4.7656F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 4.7656F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(14.0F, 0.0F, 4.7656F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 4.7656F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(14.0F, 0.0F, 4.7656F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 4.7656F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.5F, KeyframeAnimations.degreeVec(14.0F, 0.0F, 4.7656F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0F, KeyframeAnimations.degreeVec(14.0F, 0.0F, 4.7656F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5417F, KeyframeAnimations.degreeVec(-7.5093F, 0.434F, -2.4621F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.degreeVec(-7.51F, 0.43F, -2.46F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-7.5093F, 0.434F, -2.4621F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(-27.5093F, 0.434F, -2.4621F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0F, KeyframeAnimations.degreeVec(-27.5093F, 0.434F, -2.4621F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5417F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.posVec(0.0F, 0.7F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5417F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5417F, KeyframeAnimations.posVec(0.0F, -1.6F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0F, KeyframeAnimations.posVec(0.0F, -1.6F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("lhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(7.6914F, -1.0687F, -9.8883F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(7.3471F, -7.3225F, -3.3698F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(7.3471F, -7.3225F, -3.3698F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(-116.7071F, -47.3238F, 57.8584F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0F, KeyframeAnimations.degreeVec(-116.7071F, -47.3238F, 57.8584F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.5F, KeyframeAnimations.degreeVec(2.966F, -17.6459F, -19.6885F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void skill1_1(AnimationDefinition.Builder b){b.addAnimation("lhand", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0417F, KeyframeAnimations.posVec(1.8F, 0.0F, 2.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0F, KeyframeAnimations.posVec(1.8F, 0.0F, 2.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.9167F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("hand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(22.4905F, -0.2178F, -0.0047F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(22.49F, -0.22F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(26.948F, -1.5808F, 2.674F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(22.49F, -0.22F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(26.948F, -1.5808F, 2.674F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(22.49F, -0.22F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.5F, KeyframeAnimations.degreeVec(26.948F, -1.5808F, 2.674F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("hand", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("finger1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void skill1_2(AnimationDefinition.Builder b){b.addAnimation("toe2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("mild", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(5.75F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void skill1_3(AnimationDefinition.Builder b){b.addAnimation("angry", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.25F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.75F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("shock", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("close", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-65.368F, -85.0914F, 69.4151F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-98.8842F, -43.1389F, 95.4885F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-99.7736F, -32.2351F, 102.8669F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-96.182F, -21.3222F, 113.3777F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-179.6504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-164.7451F, 19.0602F, 198.9403F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-98.7921F, 40.3432F, 264.1216F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-154.6357F, 86.9786F, 200.4091F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-282.6345F, 27.6039F, 79.6914F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-350.8496F, -1.8962F, -4.2262F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-272.8958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(-269.2719F, -29.1139F, -79.9636F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(-283.8457F, -48.0609F, -82.8255F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.degreeVec(-65.368F, -85.0914F, 69.4151F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(-98.8842F, -43.1389F, 95.4885F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.degreeVec(-99.7736F, -32.2351F, 102.8669F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(-96.182F, -21.3222F, 113.3777F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(-179.6504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.degreeVec(-164.7451F, 19.0602F, 198.9403F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(-98.7921F, 40.3432F, 264.1216F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.degreeVec(-154.6357F, 86.9786F, 200.4091F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(-282.6345F, 27.6039F, 79.6914F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(-350.8496F, -1.8962F, -4.2262F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(-272.8958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.2917F, KeyframeAnimations.degreeVec(-269.2719F, -29.1139F, -79.9636F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.375F, KeyframeAnimations.degreeVec(-283.8457F, -48.0609F, -82.8255F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.degreeVec(-65.368F, -85.0914F, 69.4151F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.degreeVec(-98.8842F, -43.1389F, 95.4885F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.degreeVec(-99.7736F, -32.2351F, 102.8669F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-96.182F, -21.3222F, 113.3777F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.125F, KeyframeAnimations.degreeVec(-179.6504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.1667F, KeyframeAnimations.degreeVec(-164.7451F, 19.0602F, 198.9403F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.25F, KeyframeAnimations.degreeVec(-98.7921F, 40.3432F, 264.1216F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.3333F, KeyframeAnimations.degreeVec(-154.6357F, 86.9786F, 200.4091F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.5833F, KeyframeAnimations.degreeVec(-282.6345F, 27.6039F, 79.6914F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.6667F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.75F, KeyframeAnimations.degreeVec(-350.8496F, -1.8962F, -4.2262F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9167F, KeyframeAnimations.degreeVec(-272.8958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0417F, KeyframeAnimations.degreeVec(-269.2719F, -29.1139F, -79.9636F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.125F, KeyframeAnimations.degreeVec(-283.8457F, -48.0609F, -82.8255F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2083F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.25F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2917F, KeyframeAnimations.degreeVec(-65.368F, -85.0914F, 69.4151F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.4583F, KeyframeAnimations.degreeVec(-98.8842F, -43.1389F, 95.4885F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.625F, KeyframeAnimations.degreeVec(-99.7736F, -32.2351F, 102.8669F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.75F, KeyframeAnimations.degreeVec(-96.182F, -21.3222F, 113.3777F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.875F, KeyframeAnimations.degreeVec(-179.6504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.9167F, KeyframeAnimations.degreeVec(-164.7451F, 19.0602F, 198.9403F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(-98.7921F, 40.3432F, 264.1216F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(31.1F, 25.9F, 28.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(30.655F, 18.93F, 42.345F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(29.32F, 13.095F, 47.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(27.295F, 6.075F, 50.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(10.795F, 0.355F, 52.005F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-3.13F, 0.44F, 51.63F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-12.9F, 17.605F, 43.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(-13.43F, 25.82F, 26.41F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(-12.23F, 13.945F, 5.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(16.125F, -0.14F, -0.465F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(27.92F, 8.025F, 1.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(30.4F, 14.09F, 5.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(30.065F, 18.675F, 7.84F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.posVec(31.1F, 25.9F, 28.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(30.655F, 18.93F, 42.345F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.posVec(29.32F, 13.095F, 47.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.posVec(27.295F, 6.075F, 50.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(10.795F, 0.355F, 52.005F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.posVec(-3.13F, 0.44F, 51.63F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.posVec(-12.9F, 17.605F, 43.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.posVec(-13.43F, 25.82F, 26.41F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.posVec(-12.23F, 13.945F, 5.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.posVec(16.125F, -0.14F, -0.465F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(27.92F, 8.025F, 1.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.2917F, KeyframeAnimations.posVec(30.4F, 14.09F, 5.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.375F, KeyframeAnimations.posVec(30.065F, 18.675F, 7.84F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.posVec(31.1F, 25.9F, 28.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.posVec(30.655F, 18.93F, 42.345F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.posVec(29.32F, 13.095F, 47.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(27.295F, 6.075F, 50.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.125F, KeyframeAnimations.posVec(10.795F, 0.355F, 52.005F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.1667F, KeyframeAnimations.posVec(-3.13F, 0.44F, 51.63F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.25F, KeyframeAnimations.posVec(-12.9F, 17.605F, 43.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.3333F, KeyframeAnimations.posVec(-13.43F, 25.82F, 26.41F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.5833F, KeyframeAnimations.posVec(-12.23F, 13.945F, 5.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.6667F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.75F, KeyframeAnimations.posVec(16.125F, -0.14F, -0.465F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9167F, KeyframeAnimations.posVec(27.92F, 8.025F, 1.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0417F, KeyframeAnimations.posVec(30.4F, 14.09F, 5.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.125F, KeyframeAnimations.posVec(30.065F, 18.675F, 7.84F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2083F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.25F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2917F, KeyframeAnimations.posVec(31.1F, 25.9F, 28.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.4583F, KeyframeAnimations.posVec(30.655F, 18.93F, 42.345F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.625F, KeyframeAnimations.posVec(29.32F, 13.095F, 47.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.75F, KeyframeAnimations.posVec(27.295F, 6.075F, 50.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.875F, KeyframeAnimations.posVec(10.795F, 0.355F, 52.005F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.9167F, KeyframeAnimations.posVec(-3.13F, 0.44F, 51.63F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.posVec(-12.9F, 17.605F, 43.61F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-85.8504F, -60.4717F, 90.3553F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-102.1107F, -29.397F, 102.3709F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-113.3936F, -27.5113F, 118.085F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-159.5618F, -3.7147F, 163.529F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-172.1504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-99.5273F, 20.7275F, 241.2514F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-95.5207F, 59.9378F, 269.6097F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-263.2361F, 66.1476F, 91.2049F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-285.094F, 22.0422F, 73.2632F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-289.3974F, -33.3149F, -49.3241F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-280.3958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(-267.9874F, -48.5394F, -91.4801F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(-288.6993F, -60.0746F, -76.866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.degreeVec(-85.8504F, -60.4717F, 90.3553F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(-102.1107F, -29.397F, 102.3709F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.degreeVec(-113.3936F, -27.5113F, 118.085F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(-159.5618F, -3.7147F, 163.529F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(-172.1504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.degreeVec(-99.5273F, 20.7275F, 241.2514F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(-95.5207F, 59.9378F, 269.6097F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.degreeVec(-263.2361F, 66.1476F, 91.2049F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(-285.094F, 22.0422F, 73.2632F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(-289.3974F, -33.3149F, -49.3241F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(-280.3958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.2917F, KeyframeAnimations.degreeVec(-267.9874F, -48.5394F, -91.4801F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.375F, KeyframeAnimations.degreeVec(-288.6993F, -60.0746F, -76.866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.degreeVec(-85.8504F, -60.4717F, 90.3553F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.degreeVec(-102.1107F, -29.397F, 102.3709F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.degreeVec(-113.3936F, -27.5113F, 118.085F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-159.5618F, -3.7147F, 163.529F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.125F, KeyframeAnimations.degreeVec(-172.1504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.1667F, KeyframeAnimations.degreeVec(-99.5273F, 20.7275F, 241.2514F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.25F, KeyframeAnimations.degreeVec(-95.5207F, 59.9378F, 269.6097F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.3333F, KeyframeAnimations.degreeVec(-263.2361F, 66.1476F, 91.2049F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.5833F, KeyframeAnimations.degreeVec(-285.094F, 22.0422F, 73.2632F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.6667F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.75F, KeyframeAnimations.degreeVec(-289.3974F, -33.3149F, -49.3241F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9167F, KeyframeAnimations.degreeVec(-280.3958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0417F, KeyframeAnimations.degreeVec(-267.9874F, -48.5394F, -91.4801F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.125F, KeyframeAnimations.degreeVec(-288.6993F, -60.0746F, -76.866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2083F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.25F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2917F, KeyframeAnimations.degreeVec(-85.8504F, -60.4717F, 90.3553F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.4583F, KeyframeAnimations.degreeVec(-102.1107F, -29.397F, 102.3709F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.625F, KeyframeAnimations.degreeVec(-113.3936F, -27.5113F, 118.085F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.75F, KeyframeAnimations.degreeVec(-159.5618F, -3.7147F, 163.529F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.875F, KeyframeAnimations.degreeVec(-172.1504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.9167F, KeyframeAnimations.degreeVec(-99.5273F, 20.7275F, 241.2514F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(-95.5207F, 59.9378F, 269.6097F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void skill1_4(AnimationDefinition.Builder b){b.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(22.3F, 23.7F, 35.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(20.955F, 13.63F, 47.645F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(18.22F, 6.095F, 49.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(10.67F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(-5.505F, 0.43F, 52.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-18.18F, 5.62F, 51.18F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-22.0F, 24.005F, 36.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(-22.23F, 25.02F, 18.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(-18.03F, 5.345F, 1.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(16.125F, 1.61F, -0.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(21.295F, 14.425F, 5.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(22.1F, 20.64F, 10.86F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(22.065F, 23.475F, 14.24F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.posVec(22.3F, 23.7F, 35.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(20.955F, 13.63F, 47.645F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.posVec(18.22F, 6.095F, 49.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.posVec(10.67F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(-5.505F, 0.43F, 52.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.posVec(-18.18F, 5.62F, 51.18F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.posVec(-22.0F, 24.005F, 36.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.posVec(-22.23F, 25.02F, 18.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.posVec(-18.03F, 5.345F, 1.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.posVec(16.125F, 1.61F, -0.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(21.295F, 14.425F, 5.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.2917F, KeyframeAnimations.posVec(22.1F, 20.64F, 10.86F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.375F, KeyframeAnimations.posVec(22.065F, 23.475F, 14.24F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.posVec(22.3F, 23.7F, 35.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.posVec(20.955F, 13.63F, 47.645F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.posVec(18.22F, 6.095F, 49.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(10.67F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.125F, KeyframeAnimations.posVec(-5.505F, 0.43F, 52.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.1667F, KeyframeAnimations.posVec(-18.18F, 5.62F, 51.18F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.25F, KeyframeAnimations.posVec(-22.0F, 24.005F, 36.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.3333F, KeyframeAnimations.posVec(-22.23F, 25.02F, 18.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.5833F, KeyframeAnimations.posVec(-18.03F, 5.345F, 1.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.6667F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.75F, KeyframeAnimations.posVec(16.125F, 1.61F, -0.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9167F, KeyframeAnimations.posVec(21.295F, 14.425F, 5.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0417F, KeyframeAnimations.posVec(22.1F, 20.64F, 10.86F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.125F, KeyframeAnimations.posVec(22.065F, 23.475F, 14.24F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2083F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.25F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2917F, KeyframeAnimations.posVec(22.3F, 23.7F, 35.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.4583F, KeyframeAnimations.posVec(20.955F, 13.63F, 47.645F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.625F, KeyframeAnimations.posVec(18.22F, 6.095F, 49.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.75F, KeyframeAnimations.posVec(10.67F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.875F, KeyframeAnimations.posVec(-5.505F, 0.43F, 52.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.9167F, KeyframeAnimations.posVec(-18.18F, 5.62F, 51.18F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.posVec(-22.0F, 24.005F, 36.11F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-93.8583F, -74.9675F, 93.7266F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-86.6424F, -52.4887F, 91.303F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-105.8789F, -20.9109F, 112.5468F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-166.9536F, 7.9768F, 181.4866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-132.8382F, 37.2902F, 230.8687F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-95.6712F, 16.3924F, 246.8258F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-181.1006F, 87.2371F, 183.2895F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-265.0888F, 56.1979F, 89.0981F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-343.6514F, 15.7568F, 12.7989F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-279.8191F, -36.8748F, -65.8983F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-266.4444F, -45.0479F, -93.4194F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(-263.3136F, -78.4806F, -96.5246F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(-259.6189F, -80.5013F, -98.0023F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(-398.3715F, -78.349F, 37.0455F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-93.8583F, -74.9675F, 93.7266F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.degreeVec(-86.6424F, -52.4887F, 91.303F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(-105.8789F, -20.9109F, 112.5468F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(-166.9536F, 7.9768F, 181.4866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(-132.8382F, 37.2902F, 230.8687F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.degreeVec(-95.6712F, 16.3924F, 246.8258F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(-181.1006F, 87.2371F, 183.2895F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.degreeVec(-265.0888F, 56.1979F, 89.0981F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(-343.6514F, 15.7568F, 12.7989F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(-279.8191F, -36.8748F, -65.8983F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(-266.4444F, -45.0479F, -93.4194F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.2917F, KeyframeAnimations.degreeVec(-263.3136F, -78.4806F, -96.5246F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.375F, KeyframeAnimations.degreeVec(-259.6189F, -80.5013F, -98.0023F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.degreeVec(-398.3715F, -78.349F, 37.0455F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(-93.8583F, -74.9675F, 93.7266F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.degreeVec(-86.6424F, -52.4887F, 91.303F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.degreeVec(-105.8789F, -20.9109F, 112.5468F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-166.9536F, 7.9768F, 181.4866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.125F, KeyframeAnimations.degreeVec(-132.8382F, 37.2902F, 230.8687F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.1667F, KeyframeAnimations.degreeVec(-95.6712F, 16.3924F, 246.8258F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.25F, KeyframeAnimations.degreeVec(-181.1006F, 87.2371F, 183.2895F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.3333F, KeyframeAnimations.degreeVec(-265.0888F, 56.1979F, 89.0981F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.5833F, KeyframeAnimations.degreeVec(-343.6514F, 15.7568F, 12.7989F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.6667F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.75F, KeyframeAnimations.degreeVec(-279.8191F, -36.8748F, -65.8983F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9167F, KeyframeAnimations.degreeVec(-266.4444F, -45.0479F, -93.4194F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0417F, KeyframeAnimations.degreeVec(-263.3136F, -78.4806F, -96.5246F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.125F, KeyframeAnimations.degreeVec(-259.6189F, -80.5013F, -98.0023F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2083F, KeyframeAnimations.degreeVec(-398.3715F, -78.349F, 37.0455F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.25F, KeyframeAnimations.degreeVec(-93.8583F, -74.9675F, 93.7266F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2917F, KeyframeAnimations.degreeVec(-86.6424F, -52.4887F, 91.303F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.4583F, KeyframeAnimations.degreeVec(-105.8789F, -20.9109F, 112.5468F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.75F, KeyframeAnimations.degreeVec(-166.9536F, 7.9768F, 181.4866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.875F, KeyframeAnimations.degreeVec(-132.8382F, 37.2902F, 230.8687F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.9167F, KeyframeAnimations.degreeVec(-95.6712F, 16.3924F, 246.8258F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(-181.1006F, 87.2371F, 183.2895F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(13.8F, 20.0F, 42.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(10.58F, 7.13F, 49.67F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(1.52F, 1.27F, 52.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(-5.93F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(-22.005F, 1.13F, 52.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-29.61F, 12.62F, 47.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-30.5F, 26.105F, 26.01F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(-30.63F, 20.82F, 11.51F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(-16.68F, 0.045F, -1.09F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(11.225F, 9.11F, 1.81F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(13.095F, 20.825F, 10.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(13.4F, 24.74F, 18.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.posVec(13.8F, 20.0F, 42.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(10.58F, 7.13F, 49.67F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.posVec(1.52F, 1.27F, 52.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.posVec(-5.93F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(-22.005F, 1.13F, 52.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.posVec(-29.61F, 12.62F, 47.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.posVec(-30.5F, 26.105F, 26.01F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.posVec(-30.63F, 20.82F, 11.51F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.posVec(-16.68F, 0.045F, -1.09F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.posVec(11.225F, 9.11F, 1.81F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(13.095F, 20.825F, 10.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.2917F, KeyframeAnimations.posVec(13.4F, 24.74F, 18.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.posVec(13.8F, 20.0F, 42.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.posVec(10.58F, 7.13F, 49.67F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.posVec(1.52F, 1.27F, 52.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(-5.93F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.125F, KeyframeAnimations.posVec(-22.005F, 1.13F, 52.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.1667F, KeyframeAnimations.posVec(-29.61F, 12.62F, 47.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.25F, KeyframeAnimations.posVec(-30.5F, 26.105F, 26.01F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.3333F, KeyframeAnimations.posVec(-30.63F, 20.82F, 11.51F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.5833F, KeyframeAnimations.posVec(-16.68F, 0.045F, -1.09F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.6667F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.75F, KeyframeAnimations.posVec(11.225F, 9.11F, 1.81F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9167F, KeyframeAnimations.posVec(13.095F, 20.825F, 10.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0417F, KeyframeAnimations.posVec(13.4F, 24.74F, 18.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2083F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.25F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2917F, KeyframeAnimations.posVec(13.8F, 20.0F, 42.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.4583F, KeyframeAnimations.posVec(10.58F, 7.13F, 49.67F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.625F, KeyframeAnimations.posVec(1.52F, 1.27F, 52.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.75F, KeyframeAnimations.posVec(-5.93F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.875F, KeyframeAnimations.posVec(-22.005F, 1.13F, 52.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.9167F, KeyframeAnimations.posVec(-29.61F, 12.62F, 47.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.posVec(-30.5F, 26.105F, 26.01F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(87.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(104.3033F, 32.2362F, -246.8753F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(255.5873F, 81.4735F, -95.7486F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(267.3615F, 36.7089F, -83.0666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(362.4224F, 6.9398F, -0.8472F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(284.0933F, -81.2714F, 81.9731F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(445.2676F, -63.3915F, -79.8573F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(447.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(87.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9167F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(104.3033F, 32.2362F, -246.8753F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(255.5873F, 81.4735F, -95.7486F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.degreeVec(267.3615F, 36.7089F, -83.0666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.degreeVec(362.4224F, 6.9398F, -0.8472F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.2917F, KeyframeAnimations.degreeVec(284.0933F, -81.2714F, 81.9731F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.375F, KeyframeAnimations.degreeVec(445.2676F, -63.3915F, -79.8573F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.degreeVec(447.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(87.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(104.3033F, 32.2362F, -246.8753F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.125F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.25F, KeyframeAnimations.degreeVec(255.5873F, 81.4735F, -95.7486F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.3333F, KeyframeAnimations.degreeVec(267.3615F, 36.7089F, -83.0666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.5833F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.6667F, KeyframeAnimations.degreeVec(362.4224F, 6.9398F, -0.8472F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.75F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9167F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0417F, KeyframeAnimations.degreeVec(284.0933F, -81.2714F, 81.9731F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.125F, KeyframeAnimations.degreeVec(445.2676F, -63.3915F, -79.8573F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2083F, KeyframeAnimations.degreeVec(447.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.25F, KeyframeAnimations.degreeVec(87.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2917F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.4167F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.4583F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.625F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.75F, KeyframeAnimations.degreeVec(104.3033F, 32.2362F, -246.8753F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.875F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(255.5873F, 81.4735F, -95.7486F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-6.9F, 1.8F, -54.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(4.4F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(19.3F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(26.4F, 7.0F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(30.4F, 19.9F, -42.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(30.8F, 26.4F, -26.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(30.8F, 18.7F, -9.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(26.1F, 4.2F, -1.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-11.7F, 11.7F, -3.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(-13.1F, 25.3F, -20.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(-13.6F, 22.9F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.posVec(-6.9F, 1.8F, -54.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(4.4F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.posVec(19.3F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.posVec(26.4F, 7.0F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(30.4F, 19.9F, -42.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.posVec(30.8F, 26.4F, -26.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.posVec(30.8F, 18.7F, -9.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.posVec(26.1F, 4.2F, -1.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(-11.7F, 11.7F, -3.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.2917F, KeyframeAnimations.posVec(-13.1F, 25.3F, -20.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.375F, KeyframeAnimations.posVec(-13.6F, 22.9F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.posVec(-6.9F, 1.8F, -54.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.posVec(4.4F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.posVec(19.3F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(26.4F, 7.0F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.125F, KeyframeAnimations.posVec(30.4F, 19.9F, -42.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.25F, KeyframeAnimations.posVec(30.8F, 26.4F, -26.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.3333F, KeyframeAnimations.posVec(30.8F, 18.7F, -9.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.5833F, KeyframeAnimations.posVec(26.1F, 4.2F, -1.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.75F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9167F, KeyframeAnimations.posVec(-11.7F, 11.7F, -3.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0417F, KeyframeAnimations.posVec(-13.1F, 25.3F, -20.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.125F, KeyframeAnimations.posVec(-13.6F, 22.9F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2083F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.25F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2917F, KeyframeAnimations.posVec(-6.9F, 1.8F, -54.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.4583F, KeyframeAnimations.posVec(4.4F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.625F, KeyframeAnimations.posVec(19.3F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.75F, KeyframeAnimations.posVec(26.4F, 7.0F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.875F, KeyframeAnimations.posVec(30.4F, 19.9F, -42.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.posVec(30.8F, 26.4F, -26.6F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("bone23", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void skill1_5(AnimationDefinition.Builder b){b.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(86.6072F, -45.1048F, -100.1054F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(93.439F, -29.9049F, -107.813F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(93.5006F, -41.8759F, -111.9761F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(177.5572F, -5.9959F, -197.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(102.3852F, 30.2628F, -239.017F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(95.8788F, 39.0284F, -264.8805F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(97.3809F, 88.5476F, -264.539F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(273.0528F, 42.9326F, -81.7794F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(280.8679F, 37.7136F, -69.9546F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(355.4252F, -2.9184F, 6.5199F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(273.719F, -19.6137F, 80.6991F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(268.4388F, -61.5322F, 98.1823F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.degreeVec(86.6072F, -45.1048F, -100.1054F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9167F, KeyframeAnimations.degreeVec(93.439F, -29.9049F, -107.813F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(93.5006F, -41.8759F, -111.9761F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(177.5572F, -5.9959F, -197.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(102.3852F, 30.2628F, -239.017F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(95.8788F, 39.0284F, -264.8805F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.degreeVec(97.3809F, 88.5476F, -264.539F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(273.0528F, 42.9326F, -81.7794F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.degreeVec(280.8679F, 37.7136F, -69.9546F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(355.4252F, -2.9184F, 6.5199F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.2917F, KeyframeAnimations.degreeVec(273.719F, -19.6137F, 80.6991F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.375F, KeyframeAnimations.degreeVec(268.4388F, -61.5322F, 98.1823F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.degreeVec(86.6072F, -45.1048F, -100.1054F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.degreeVec(93.439F, -29.9049F, -107.813F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.degreeVec(93.5006F, -41.8759F, -111.9761F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(177.5572F, -5.9959F, -197.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.125F, KeyframeAnimations.degreeVec(102.3852F, 30.2628F, -239.017F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.25F, KeyframeAnimations.degreeVec(95.8788F, 39.0284F, -264.8805F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.3333F, KeyframeAnimations.degreeVec(97.3809F, 88.5476F, -264.539F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.5833F, KeyframeAnimations.degreeVec(273.0528F, 42.9326F, -81.7794F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.6667F, KeyframeAnimations.degreeVec(280.8679F, 37.7136F, -69.9546F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.75F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9167F, KeyframeAnimations.degreeVec(355.4252F, -2.9184F, 6.5199F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0417F, KeyframeAnimations.degreeVec(273.719F, -19.6137F, 80.6991F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.125F, KeyframeAnimations.degreeVec(268.4388F, -61.5322F, 98.1823F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2083F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.25F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2917F, KeyframeAnimations.degreeVec(86.6072F, -45.1048F, -100.1054F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.3333F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.4167F, KeyframeAnimations.degreeVec(93.439F, -29.9049F, -107.813F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.4583F, KeyframeAnimations.degreeVec(93.5006F, -41.8759F, -111.9761F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.625F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.75F, KeyframeAnimations.degreeVec(177.5572F, -5.9959F, -197.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.875F, KeyframeAnimations.degreeVec(102.3852F, 30.2628F, -239.017F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(95.8788F, 39.0284F, -264.8805F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-30.81F, 14.6F, -46.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(-29.0F, 7.5F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(-16.6F, -0.1F, -52.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(-5.1F, 0.4F, -52.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(9.2F, 5.1F, -51.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(12.7F, 16.9F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(13.4F, 26.5F, -24.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(13.25F, 17.4F, -9.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(10.06F, 6.69F, -3.16F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-18.8F, -0.1F, -0.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(-29.95F, 13.9F, -5.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(-30.2F, 24.9F, -19.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.posVec(-30.81F, 14.6F, -46.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(-29.0F, 7.5F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.posVec(-16.6F, -0.1F, -52.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.posVec(-5.1F, 0.4F, -52.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(9.2F, 5.1F, -51.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.posVec(12.7F, 16.9F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.posVec(13.4F, 26.5F, -24.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.posVec(13.25F, 17.4F, -9.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.posVec(10.06F, 6.69F, -3.16F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(-18.8F, -0.1F, -0.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.2917F, KeyframeAnimations.posVec(-29.95F, 13.9F, -5.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.375F, KeyframeAnimations.posVec(-30.2F, 24.9F, -19.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.posVec(-30.81F, 14.6F, -46.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.posVec(-29.0F, 7.5F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.posVec(-16.6F, -0.1F, -52.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(-5.1F, 0.4F, -52.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.125F, KeyframeAnimations.posVec(9.2F, 5.1F, -51.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.25F, KeyframeAnimations.posVec(12.7F, 16.9F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.3333F, KeyframeAnimations.posVec(13.4F, 26.5F, -24.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.5833F, KeyframeAnimations.posVec(13.25F, 17.4F, -9.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.6667F, KeyframeAnimations.posVec(10.06F, 6.69F, -3.16F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.75F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9167F, KeyframeAnimations.posVec(-18.8F, -0.1F, -0.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0417F, KeyframeAnimations.posVec(-29.95F, 13.9F, -5.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.125F, KeyframeAnimations.posVec(-30.2F, 24.9F, -19.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2083F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.25F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2917F, KeyframeAnimations.posVec(-30.81F, 14.6F, -46.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.4583F, KeyframeAnimations.posVec(-29.0F, 7.5F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.625F, KeyframeAnimations.posVec(-16.6F, -0.1F, -52.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.75F, KeyframeAnimations.posVec(-5.1F, 0.4F, -52.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.875F, KeyframeAnimations.posVec(9.2F, 5.1F, -51.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.posVec(12.7F, 16.9F, -45.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("bone24", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(181.0207F, -21.4331F, -170.8872F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(161.0027F, 14.8667F, -204.743F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(103.895F, 70.6504F, -255.4522F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(265.5404F, 61.6655F, -85.4158F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(308.2499F, 31.2143F, -27.8326F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(273.0766F, -46.489F, 93.6773F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(442.5951F, -73.3384F, -76.9932F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9167F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(181.0207F, -21.4331F, -170.8872F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(161.0027F, 14.8667F, -204.743F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(103.895F, 70.6504F, -255.4522F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.degreeVec(265.5404F, 61.6655F, -85.4158F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.degreeVec(308.2499F, 31.2143F, -27.8326F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.2917F, KeyframeAnimations.degreeVec(273.0766F, -46.489F, 93.6773F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.375F, KeyframeAnimations.degreeVec(442.5951F, -73.3384F, -76.9932F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.degreeVec(181.0207F, -21.4331F, -170.8872F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(161.0027F, 14.8667F, -204.743F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.125F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.25F, KeyframeAnimations.degreeVec(103.895F, 70.6504F, -255.4522F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.3333F, KeyframeAnimations.degreeVec(265.5404F, 61.6655F, -85.4158F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.5833F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.6667F, KeyframeAnimations.degreeVec(308.2499F, 31.2143F, -27.8326F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.75F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9167F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0417F, KeyframeAnimations.degreeVec(273.0766F, -46.489F, 93.6773F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.125F, KeyframeAnimations.degreeVec(442.5951F, -73.3384F, -76.9932F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2083F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.25F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2917F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.3333F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.4167F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.4583F, KeyframeAnimations.degreeVec(181.0207F, -21.4331F, -170.8872F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.625F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.75F, KeyframeAnimations.degreeVec(161.0027F, 14.8667F, -204.743F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.875F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(103.895F, 70.6504F, -255.4522F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-18.51F, 7.3F, -51.07F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(-13.6F, -0.1F, -50.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(1.7F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(11.5F, 0.4F, -53.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(20.4F, 12.3F, -48.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(22.0F, 22.6F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(22.2F, 23.8F, -15.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.posVec(20.4F, 11.1F, -3.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(13.46F, 0.99F, 0.44F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-17.2F, 4.2F, -1.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.posVec(-21.7F, 20.5F, -11.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(-21.9F, 26.6F, -28.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7917F, KeyframeAnimations.posVec(-18.51F, 7.3F, -51.07F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(-13.6F, -0.1F, -50.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.posVec(1.7F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.posVec(11.5F, 0.4F, -53.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(20.4F, 12.3F, -48.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.posVec(22.0F, 22.6F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.posVec(22.2F, 23.8F, -15.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.posVec(20.4F, 11.1F, -3.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9167F, KeyframeAnimations.posVec(13.46F, 0.99F, 0.44F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(-17.2F, 4.2F, -1.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.2917F, KeyframeAnimations.posVec(-21.7F, 20.5F, -11.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.375F, KeyframeAnimations.posVec(-21.9F, 26.6F, -28.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.posVec(-18.51F, 7.3F, -51.07F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.posVec(-13.6F, -0.1F, -50.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.posVec(1.7F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(11.5F, 0.4F, -53.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.125F, KeyframeAnimations.posVec(20.4F, 12.3F, -48.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.25F, KeyframeAnimations.posVec(22.0F, 22.6F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.3333F, KeyframeAnimations.posVec(22.2F, 23.8F, -15.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.5833F, KeyframeAnimations.posVec(20.4F, 11.1F, -3.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.6667F, KeyframeAnimations.posVec(13.46F, 0.99F, 0.44F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.75F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9167F, KeyframeAnimations.posVec(-17.2F, 4.2F, -1.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0417F, KeyframeAnimations.posVec(-21.7F, 20.5F, -11.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.125F, KeyframeAnimations.posVec(-21.9F, 26.6F, -28.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2083F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.25F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2917F, KeyframeAnimations.posVec(-18.51F, 7.3F, -51.07F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.4583F, KeyframeAnimations.posVec(-13.6F, -0.1F, -50.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.625F, KeyframeAnimations.posVec(1.7F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.75F, KeyframeAnimations.posVec(11.5F, 0.4F, -53.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.875F, KeyframeAnimations.posVec(20.4F, 12.3F, -48.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.posVec(22.0F, 22.6F, -37.1F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("bone25", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(5.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void skill1_6(AnimationDefinition.Builder b){b.addAnimation("rhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-51.1822F, -21.8091F, -43.9846F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.degreeVec(-51.6912F, -17.0718F, -42.3031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-51.1822F, -21.8091F, -43.9846F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(-109.0222F, -38.8611F, -68.7849F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(-102.1103F, -34.6662F, -72.1106F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(-109.0222F, -38.8611F, -68.7849F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(-102.1103F, -34.6662F, -72.1106F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-109.0222F, -38.8611F, -68.7849F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0F, KeyframeAnimations.degreeVec(-109.0222F, -38.8611F, -68.7849F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2917F, KeyframeAnimations.degreeVec(6.0086F, 10.9067F, -5.8924F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.4583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("rhand", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.4583F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("hand2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 52.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 52.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(5.4583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("hand2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0417F, KeyframeAnimations.posVec(-1.2F, -0.8F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(5.0F, KeyframeAnimations.posVec(-1.2F, -0.8F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(5.4583F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void skill1_7(AnimationDefinition.Builder b){b.addAnimation("finger6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9167F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.25F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.625F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.25F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.4167F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.625F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.8333F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("finger7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9167F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9583F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.9583F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.4167F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.5833F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.7917F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("finger8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.75F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9583F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.75F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.9583F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.1667F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.5833F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.75F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.1667F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0417F, KeyframeAnimations.degreeVec(57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
public static final AnimationDefinition skill2=build_skill2();
private static AnimationDefinition build_skill2(){var b=AnimationDefinition.Builder.withLength(4.0F);skill2_0(b);skill2_1(b);skill2_2(b);skill2_3(b);skill2_4(b);skill2_5(b);return b.build();}
private static void skill2_0(AnimationDefinition.Builder b){b.addAnimation("main", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("main", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("wheel2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(12.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.degreeVec(12.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(12.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.9167F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("wheel2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.9167F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.2917F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.posVec(0.0F, 1.6F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.2917F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.posVec(0.0F, 1.6F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void skill2_1(AnimationDefinition.Builder b){b.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0833F, KeyframeAnimations.degreeVec(2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.875F, KeyframeAnimations.degreeVec(2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.625F, KeyframeAnimations.degreeVec(2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.875F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0833F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.875F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.25F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.625F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("lhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -22.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-2.7018F, 13.4467F, -5.6182F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(-2.7018F, 13.4467F, -5.6182F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -22.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -22.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("hand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 3.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 17.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("mild", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.25F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("angry", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.7917F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(4.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void skill2_2(AnimationDefinition.Builder b){b.addAnimation("shock", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("close", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-65.368F, -85.0914F, 69.4151F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-98.8842F, -43.1389F, 95.4885F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-99.7736F, -32.2351F, 102.8669F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-96.182F, -21.3222F, 113.3777F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-179.6504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-164.7451F, 19.0602F, 198.9403F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(-98.7921F, 40.3432F, 264.1216F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-154.6357F, 86.9786F, 200.4091F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.degreeVec(-282.6345F, 27.6039F, 79.6914F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.degreeVec(-350.8496F, -1.8962F, -4.2262F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.degreeVec(-272.8958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.degreeVec(-269.2719F, -29.1139F, -79.9636F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(-283.8457F, -48.0609F, -82.8255F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.degreeVec(-65.368F, -85.0914F, 69.4151F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.degreeVec(-98.8842F, -43.1389F, 95.4885F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-99.7736F, -32.2351F, 102.8669F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-96.182F, -21.3222F, 113.3777F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(-179.6504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.875F, KeyframeAnimations.degreeVec(-164.7451F, 19.0602F, 198.9403F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-350.8496F, -1.8962F, -4.2262F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0833F, KeyframeAnimations.degreeVec(-272.8958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.1667F, KeyframeAnimations.degreeVec(-269.2719F, -29.1139F, -79.9636F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(-283.8457F, -48.0609F, -82.8255F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.degreeVec(-65.368F, -85.0914F, 69.4151F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(-98.8842F, -43.1389F, 95.4885F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.degreeVec(-99.7736F, -32.2351F, 102.8669F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(-96.182F, -21.3222F, 113.3777F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.degreeVec(-179.6504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(-164.7451F, 19.0602F, 198.9403F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0417F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(-350.8496F, -1.8962F, -4.2262F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(-272.8958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.25F, KeyframeAnimations.degreeVec(-269.2719F, -29.1139F, -79.9636F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(-283.8457F, -48.0609F, -82.8255F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(-65.368F, -85.0914F, 69.4151F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.625F, KeyframeAnimations.degreeVec(-98.8842F, -43.1389F, 95.4885F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.degreeVec(-99.7736F, -32.2351F, 102.8669F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(-96.182F, -21.3222F, 113.3777F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.degreeVec(-179.6504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.9167F, KeyframeAnimations.degreeVec(-164.7451F, 19.0602F, 198.9403F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-98.7921F, 40.3432F, 264.1216F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(31.1F, 25.9F, 28.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(30.655F, 18.93F, 42.345F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(29.32F, 13.095F, 47.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(27.295F, 6.075F, 50.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(10.795F, 0.355F, 52.005F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-3.13F, 0.44F, 51.63F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.posVec(-12.9F, 17.605F, 43.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-13.43F, 25.82F, 26.41F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.posVec(-12.23F, 13.945F, 5.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.posVec(16.125F, -0.14F, -0.465F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.posVec(27.92F, 8.025F, 1.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.posVec(30.4F, 14.09F, 5.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.posVec(30.065F, 18.675F, 7.84F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.posVec(31.1F, 25.9F, 28.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.posVec(30.655F, 18.93F, 42.345F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.posVec(29.32F, 13.095F, 47.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(27.295F, 6.075F, 50.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.posVec(10.795F, 0.355F, 52.005F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.875F, KeyframeAnimations.posVec(-3.13F, 0.44F, 51.63F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.posVec(16.125F, -0.14F, -0.465F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0833F, KeyframeAnimations.posVec(27.92F, 8.025F, 1.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.1667F, KeyframeAnimations.posVec(30.4F, 14.09F, 5.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.posVec(30.065F, 18.675F, 7.84F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.posVec(31.1F, 25.9F, 28.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.posVec(30.655F, 18.93F, 42.345F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.posVec(29.32F, 13.095F, 47.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.posVec(27.295F, 6.075F, 50.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.posVec(10.795F, 0.355F, 52.005F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.posVec(-3.13F, 0.44F, 51.63F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0417F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.posVec(16.125F, -0.14F, -0.465F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(27.92F, 8.025F, 1.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.25F, KeyframeAnimations.posVec(30.4F, 14.09F, 5.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.posVec(30.065F, 18.675F, 7.84F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(31.1F, 25.9F, 28.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.625F, KeyframeAnimations.posVec(30.655F, 18.93F, 42.345F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.posVec(29.32F, 13.095F, 47.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.posVec(27.295F, 6.075F, 50.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.posVec(10.795F, 0.355F, 52.005F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.9167F, KeyframeAnimations.posVec(-3.13F, 0.44F, 51.63F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(-12.9F, 17.605F, 43.61F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-85.8504F, -60.4717F, 90.3553F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-102.1107F, -29.397F, 102.3709F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-113.3936F, -27.5113F, 118.085F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-159.5618F, -3.7147F, 163.529F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-172.1504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-99.5273F, 20.7275F, 241.2514F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(-95.5207F, 59.9378F, 269.6097F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-263.2361F, 66.1476F, 91.2049F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.degreeVec(-285.094F, 22.0422F, 73.2632F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.degreeVec(-289.3974F, -33.3149F, -49.3241F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.degreeVec(-280.3958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.degreeVec(-267.9874F, -48.5394F, -91.4801F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(-288.6993F, -60.0746F, -76.866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.degreeVec(-85.8504F, -60.4717F, 90.3553F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.degreeVec(-102.1107F, -29.397F, 102.3709F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-113.3936F, -27.5113F, 118.085F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-159.5618F, -3.7147F, 163.529F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(-172.1504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.875F, KeyframeAnimations.degreeVec(-99.5273F, 20.7275F, 241.2514F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-289.3974F, -33.3149F, -49.3241F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0833F, KeyframeAnimations.degreeVec(-280.3958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.1667F, KeyframeAnimations.degreeVec(-267.9874F, -48.5394F, -91.4801F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(-288.6993F, -60.0746F, -76.866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.degreeVec(-85.8504F, -60.4717F, 90.3553F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(-102.1107F, -29.397F, 102.3709F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.degreeVec(-113.3936F, -27.5113F, 118.085F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(-159.5618F, -3.7147F, 163.529F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.degreeVec(-172.1504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(-99.5273F, 20.7275F, 241.2514F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0417F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(-289.3974F, -33.3149F, -49.3241F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(-280.3958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.25F, KeyframeAnimations.degreeVec(-267.9874F, -48.5394F, -91.4801F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(-288.6993F, -60.0746F, -76.866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(-85.8504F, -60.4717F, 90.3553F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.625F, KeyframeAnimations.degreeVec(-102.1107F, -29.397F, 102.3709F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.degreeVec(-113.3936F, -27.5113F, 118.085F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(-159.5618F, -3.7147F, 163.529F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.degreeVec(-172.1504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.9167F, KeyframeAnimations.degreeVec(-99.5273F, 20.7275F, 241.2514F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-95.5207F, 59.9378F, 269.6097F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(22.3F, 23.7F, 35.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(20.955F, 13.63F, 47.645F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(18.22F, 6.095F, 49.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(10.67F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(-5.505F, 0.43F, 52.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-18.18F, 5.62F, 51.18F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.posVec(-22.0F, 24.005F, 36.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-22.23F, 25.02F, 18.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.posVec(-18.03F, 5.345F, 1.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.posVec(16.125F, 1.61F, -0.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.posVec(21.295F, 14.425F, 5.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.posVec(22.1F, 20.64F, 10.86F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.posVec(22.065F, 23.475F, 14.24F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.posVec(22.3F, 23.7F, 35.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.posVec(20.955F, 13.63F, 47.645F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.posVec(18.22F, 6.095F, 49.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(10.67F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.posVec(-5.505F, 0.43F, 52.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.875F, KeyframeAnimations.posVec(-18.18F, 5.62F, 51.18F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.posVec(16.125F, 1.61F, -0.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0833F, KeyframeAnimations.posVec(21.295F, 14.425F, 5.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.1667F, KeyframeAnimations.posVec(22.1F, 20.64F, 10.86F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.posVec(22.065F, 23.475F, 14.24F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.posVec(22.3F, 23.7F, 35.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.posVec(20.955F, 13.63F, 47.645F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.posVec(18.22F, 6.095F, 49.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.posVec(10.67F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.posVec(-5.505F, 0.43F, 52.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.posVec(-18.18F, 5.62F, 51.18F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0417F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.posVec(16.125F, 1.61F, -0.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(21.295F, 14.425F, 5.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.25F, KeyframeAnimations.posVec(22.1F, 20.64F, 10.86F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.posVec(22.065F, 23.475F, 14.24F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(22.3F, 23.7F, 35.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.625F, KeyframeAnimations.posVec(20.955F, 13.63F, 47.645F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.posVec(18.22F, 6.095F, 49.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.posVec(10.67F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.posVec(-5.505F, 0.43F, 52.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.9167F, KeyframeAnimations.posVec(-18.18F, 5.62F, 51.18F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(-22.0F, 24.005F, 36.11F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void skill2_3(AnimationDefinition.Builder b){b.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-93.8583F, -74.9675F, 93.7266F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-86.6424F, -52.4887F, 91.303F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-105.8789F, -20.9109F, 112.5468F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-166.9536F, 7.9768F, 181.4866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-132.8382F, 37.2902F, 230.8687F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-95.6712F, 16.3924F, 246.8258F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(-181.1006F, 87.2371F, 183.2895F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-265.0888F, 56.1979F, 89.0981F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.degreeVec(-343.6514F, 15.7568F, 12.7989F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.degreeVec(-279.8191F, -36.8748F, -65.8983F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.degreeVec(-266.4444F, -45.0479F, -93.4194F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.degreeVec(-263.3136F, -78.4806F, -96.5246F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(-259.6189F, -80.5013F, -98.0023F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(-398.3715F, -78.349F, 37.0455F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-93.8583F, -74.9675F, 93.7266F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.degreeVec(-86.6424F, -52.4887F, 91.303F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.degreeVec(-105.8789F, -20.9109F, 112.5468F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-166.9536F, 7.9768F, 181.4866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(-132.8382F, 37.2902F, 230.8687F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.875F, KeyframeAnimations.degreeVec(-95.6712F, 16.3924F, 246.8258F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-279.8191F, -36.8748F, -65.8983F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0833F, KeyframeAnimations.degreeVec(-266.4444F, -45.0479F, -93.4194F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.1667F, KeyframeAnimations.degreeVec(-263.3136F, -78.4806F, -96.5246F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(-259.6189F, -80.5013F, -98.0023F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.degreeVec(-398.3715F, -78.349F, 37.0455F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(-93.8583F, -74.9675F, 93.7266F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.degreeVec(-86.6424F, -52.4887F, 91.303F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(-105.8789F, -20.9109F, 112.5468F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(-166.9536F, 7.9768F, 181.4866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.degreeVec(-132.8382F, 37.2902F, 230.8687F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(-95.6712F, 16.3924F, 246.8258F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0417F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(-279.8191F, -36.8748F, -65.8983F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(-266.4444F, -45.0479F, -93.4194F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.25F, KeyframeAnimations.degreeVec(-263.3136F, -78.4806F, -96.5246F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(-259.6189F, -80.5013F, -98.0023F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.degreeVec(-398.3715F, -78.349F, 37.0455F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.degreeVec(-93.8583F, -74.9675F, 93.7266F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(-86.6424F, -52.4887F, 91.303F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.625F, KeyframeAnimations.degreeVec(-105.8789F, -20.9109F, 112.5468F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(-166.9536F, 7.9768F, 181.4866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.degreeVec(-132.8382F, 37.2902F, 230.8687F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.9167F, KeyframeAnimations.degreeVec(-95.6712F, 16.3924F, 246.8258F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-181.1006F, 87.2371F, 183.2895F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(13.8F, 20.0F, 42.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(10.58F, 7.13F, 49.67F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(1.52F, 1.27F, 52.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(-5.93F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(-22.005F, 1.13F, 52.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-29.61F, 12.62F, 47.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.posVec(-30.5F, 26.105F, 26.01F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-30.63F, 20.82F, 11.51F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.posVec(-16.68F, 0.045F, -1.09F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.posVec(11.225F, 9.11F, 1.81F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.posVec(13.095F, 20.825F, 10.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.posVec(13.4F, 24.74F, 18.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.posVec(13.8F, 20.0F, 42.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.posVec(10.58F, 7.13F, 49.67F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.posVec(1.52F, 1.27F, 52.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(-5.93F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.posVec(-22.005F, 1.13F, 52.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.875F, KeyframeAnimations.posVec(-29.61F, 12.62F, 47.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.posVec(11.225F, 9.11F, 1.81F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0833F, KeyframeAnimations.posVec(13.095F, 20.825F, 10.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.1667F, KeyframeAnimations.posVec(13.4F, 24.74F, 18.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.posVec(13.8F, 20.0F, 42.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.posVec(10.58F, 7.13F, 49.67F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.posVec(1.52F, 1.27F, 52.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.posVec(-5.93F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.posVec(-22.005F, 1.13F, 52.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.posVec(-29.61F, 12.62F, 47.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0417F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.posVec(11.225F, 9.11F, 1.81F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(13.095F, 20.825F, 10.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.25F, KeyframeAnimations.posVec(13.4F, 24.74F, 18.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(13.8F, 20.0F, 42.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.625F, KeyframeAnimations.posVec(10.58F, 7.13F, 49.67F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.posVec(1.52F, 1.27F, 52.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.posVec(-5.93F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.posVec(-22.005F, 1.13F, 52.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.9167F, KeyframeAnimations.posVec(-29.61F, 12.62F, 47.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(-30.5F, 26.105F, 26.01F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(87.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(104.3033F, 32.2362F, -246.8753F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(255.5873F, 81.4735F, -95.7486F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(267.3615F, 36.7089F, -83.0666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(362.4224F, 6.9398F, -0.8472F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.degreeVec(284.0933F, -81.2714F, 81.9731F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(445.2676F, -63.3915F, -79.8573F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(447.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(87.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(104.3033F, 32.2362F, -246.8753F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(362.4224F, 6.9398F, -0.8472F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0833F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.1667F, KeyframeAnimations.degreeVec(284.0933F, -81.2714F, 81.9731F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(445.2676F, -63.3915F, -79.8573F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.degreeVec(447.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(87.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(104.3033F, 32.2362F, -246.8753F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0417F, KeyframeAnimations.degreeVec(362.4224F, 6.9398F, -0.8472F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.25F, KeyframeAnimations.degreeVec(284.0933F, -81.2714F, 81.9731F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(445.2676F, -63.3915F, -79.8573F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.degreeVec(447.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.degreeVec(87.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.625F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(104.3033F, 32.2362F, -246.8753F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(255.5873F, 81.4735F, -95.7486F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-6.9F, 1.8F, -54.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(4.4F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(19.3F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(26.4F, 7.0F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(30.4F, 19.9F, -42.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.posVec(30.8F, 26.4F, -26.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(30.8F, 18.7F, -9.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.posVec(26.1F, 4.2F, -1.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.posVec(-11.7F, 11.7F, -3.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.posVec(-13.1F, 25.3F, -20.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.posVec(-13.6F, 22.9F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.posVec(-6.9F, 1.8F, -54.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.posVec(4.4F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.posVec(19.3F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(26.4F, 7.0F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.posVec(30.4F, 19.9F, -42.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(30.8F, 26.4F, -26.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0833F, KeyframeAnimations.posVec(-11.7F, 11.7F, -3.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.1667F, KeyframeAnimations.posVec(-13.1F, 25.3F, -20.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.posVec(-13.6F, 22.9F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.posVec(-6.9F, 1.8F, -54.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.posVec(4.4F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.posVec(19.3F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.posVec(26.4F, 7.0F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.posVec(30.4F, 19.9F, -42.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0417F, KeyframeAnimations.posVec(30.8F, 26.4F, -26.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(-11.7F, 11.7F, -3.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.25F, KeyframeAnimations.posVec(-13.1F, 25.3F, -20.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.posVec(-13.6F, 22.9F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(-6.9F, 1.8F, -54.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.625F, KeyframeAnimations.posVec(4.4F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.posVec(19.3F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.posVec(26.4F, 7.0F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.posVec(30.4F, 19.9F, -42.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(30.8F, 26.4F, -26.6F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("bone23", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(86.6072F, -45.1048F, -100.1054F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(93.439F, -29.9049F, -107.813F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(93.5006F, -41.8759F, -111.9761F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(177.5572F, -5.9959F, -197.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(102.3852F, 30.2628F, -239.017F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(95.8788F, 39.0284F, -264.8805F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(97.3809F, 88.5476F, -264.539F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.degreeVec(273.0528F, 42.9326F, -81.7794F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(280.8679F, 37.7136F, -69.9546F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.degreeVec(355.4252F, -2.9184F, 6.5199F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.degreeVec(273.719F, -19.6137F, 80.6991F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(268.4388F, -61.5322F, 98.1823F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.degreeVec(86.6072F, -45.1048F, -100.1054F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.degreeVec(93.5006F, -41.8759F, -111.9761F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(177.5572F, -5.9959F, -197.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(102.3852F, 30.2628F, -239.017F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(280.8679F, 37.7136F, -69.9546F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0833F, KeyframeAnimations.degreeVec(355.4252F, -2.9184F, 6.5199F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.1667F, KeyframeAnimations.degreeVec(273.719F, -19.6137F, 80.6991F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(268.4388F, -61.5322F, 98.1823F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.degreeVec(86.6072F, -45.1048F, -100.1054F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(93.5006F, -41.8759F, -111.9761F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(177.5572F, -5.9959F, -197.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.degreeVec(102.3852F, 30.2628F, -239.017F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0417F, KeyframeAnimations.degreeVec(280.8679F, 37.7136F, -69.9546F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(355.4252F, -2.9184F, 6.5199F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.25F, KeyframeAnimations.degreeVec(273.719F, -19.6137F, 80.6991F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(268.4388F, -61.5322F, 98.1823F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(86.6072F, -45.1048F, -100.1054F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.625F, KeyframeAnimations.degreeVec(93.5006F, -41.8759F, -111.9761F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(177.5572F, -5.9959F, -197.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.degreeVec(102.3852F, 30.2628F, -239.017F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(95.8788F, 39.0284F, -264.8805F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void skill2_4(AnimationDefinition.Builder b){b.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-30.81F, 14.6F, -46.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(-29.0F, 7.5F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(-16.6F, -0.1F, -52.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(-5.1F, 0.4F, -52.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(9.2F, 5.1F, -51.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.posVec(12.7F, 16.9F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(13.4F, 26.5F, -24.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.posVec(13.25F, 17.4F, -9.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.posVec(10.06F, 6.69F, -3.16F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.posVec(-18.8F, -0.1F, -0.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.posVec(-29.95F, 13.9F, -5.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.posVec(-30.2F, 24.9F, -19.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.posVec(-30.81F, 14.6F, -46.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.posVec(-29.0F, 7.5F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.posVec(-16.6F, -0.1F, -52.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(-5.1F, 0.4F, -52.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.posVec(9.2F, 5.1F, -51.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(10.06F, 6.69F, -3.16F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0833F, KeyframeAnimations.posVec(-18.8F, -0.1F, -0.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.1667F, KeyframeAnimations.posVec(-29.95F, 13.9F, -5.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.posVec(-30.2F, 24.9F, -19.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.posVec(-30.81F, 14.6F, -46.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.posVec(-29.0F, 7.5F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.posVec(-16.6F, -0.1F, -52.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.posVec(-5.1F, 0.4F, -52.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.posVec(9.2F, 5.1F, -51.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0417F, KeyframeAnimations.posVec(10.06F, 6.69F, -3.16F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(-18.8F, -0.1F, -0.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.25F, KeyframeAnimations.posVec(-29.95F, 13.9F, -5.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.posVec(-30.2F, 24.9F, -19.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(-30.81F, 14.6F, -46.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.625F, KeyframeAnimations.posVec(-29.0F, 7.5F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.posVec(-16.6F, -0.1F, -52.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.posVec(-5.1F, 0.4F, -52.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.posVec(9.2F, 5.1F, -51.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(12.7F, 16.9F, -45.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("bone24", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(181.0207F, -21.4331F, -170.8872F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(161.0027F, 14.8667F, -204.743F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(103.895F, 70.6504F, -255.4522F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(265.5404F, 61.6655F, -85.4158F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(308.2499F, 31.2143F, -27.8326F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.degreeVec(273.0766F, -46.489F, 93.6773F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(442.5951F, -73.3384F, -76.9932F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.degreeVec(181.0207F, -21.4331F, -170.8872F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(161.0027F, 14.8667F, -204.743F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(308.2499F, 31.2143F, -27.8326F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0833F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.1667F, KeyframeAnimations.degreeVec(273.0766F, -46.489F, 93.6773F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(442.5951F, -73.3384F, -76.9932F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(181.0207F, -21.4331F, -170.8872F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(161.0027F, 14.8667F, -204.743F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0417F, KeyframeAnimations.degreeVec(308.2499F, 31.2143F, -27.8326F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.25F, KeyframeAnimations.degreeVec(273.0766F, -46.489F, 93.6773F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(442.5951F, -73.3384F, -76.9932F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.625F, KeyframeAnimations.degreeVec(181.0207F, -21.4331F, -170.8872F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(161.0027F, 14.8667F, -204.743F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(103.895F, 70.6504F, -255.4522F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-18.51F, 7.3F, -51.07F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(-13.6F, -0.1F, -50.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(1.7F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(11.5F, 0.4F, -53.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(20.4F, 12.3F, -48.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.posVec(22.0F, 22.6F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(22.2F, 23.8F, -15.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.posVec(20.4F, 11.1F, -3.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.posVec(13.46F, 0.99F, 0.44F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.posVec(-17.2F, 4.2F, -1.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.posVec(-21.7F, 20.5F, -11.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.posVec(-21.9F, 26.6F, -28.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.posVec(-18.51F, 7.3F, -51.07F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.posVec(-13.6F, -0.1F, -50.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.posVec(1.7F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(11.5F, 0.4F, -53.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.posVec(20.4F, 12.3F, -48.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.posVec(13.46F, 0.99F, 0.44F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0833F, KeyframeAnimations.posVec(-17.2F, 4.2F, -1.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.1667F, KeyframeAnimations.posVec(-21.7F, 20.5F, -11.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.posVec(-21.9F, 26.6F, -28.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.posVec(-18.51F, 7.3F, -51.07F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.posVec(-13.6F, -0.1F, -50.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.posVec(1.7F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.posVec(11.5F, 0.4F, -53.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7917F, KeyframeAnimations.posVec(20.4F, 12.3F, -48.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0417F, KeyframeAnimations.posVec(13.46F, 0.99F, 0.44F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(-17.2F, 4.2F, -1.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.25F, KeyframeAnimations.posVec(-21.7F, 20.5F, -11.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.posVec(-21.9F, 26.6F, -28.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4167F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.posVec(-18.51F, 7.3F, -51.07F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.625F, KeyframeAnimations.posVec(-13.6F, -0.1F, -50.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.posVec(1.7F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.posVec(11.5F, 0.4F, -53.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.875F, KeyframeAnimations.posVec(20.4F, 12.3F, -48.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(22.0F, 22.6F, -37.1F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("rhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(7.6914F, 1.0687F, 19.8883F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-11.4009F, -25.6346F, 12.2649F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(-11.4009F, -25.6346F, 12.2649F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(7.6914F, 1.0687F, 19.8883F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.9167F, KeyframeAnimations.degreeVec(7.6914F, 1.0687F, 19.8883F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("hand2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -3.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void skill2_5(AnimationDefinition.Builder b){b.addAnimation("horn1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("horn2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
public static final AnimationDefinition dash_intro=build_dash_intro();
private static AnimationDefinition build_dash_intro(){var b=AnimationDefinition.Builder.withLength(0.6667F);dash_intro_0(b);dash_intro_1(b);dash_intro_2(b);dash_intro_3(b);dash_intro_4(b);return b.build();}
private static void dash_intro_0(AnimationDefinition.Builder b){b.addAnimation("main", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("main", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.posVec(0.0F, 2.4F, 5.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.posVec(0.0F, 2.4F, 5.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(0.0F, 2.4F, 15.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.posVec(0.0F, 1.6F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.posVec(0.0F, -1.1F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void dash_intro_1(AnimationDefinition.Builder b){b.addAnimation("lhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(38.6289F, -18.7176F, 0.8921F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(30.0972F, -23.8933F, 22.4743F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-8.7667F, -19.3784F, 32.1983F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("hand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -22.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("mild", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.25F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("angry", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("shock", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("close", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void dash_intro_2(AnimationDefinition.Builder b){b.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-65.368F, -85.0914F, 69.4151F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-98.8842F, -43.1389F, 95.4885F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-99.7736F, -32.2351F, 102.8669F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-96.182F, -21.3222F, 113.3777F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-179.6504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(31.1F, 25.9F, 28.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(30.655F, 18.93F, 42.345F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(29.32F, 13.095F, 47.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(27.295F, 6.075F, 50.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(10.795F, 0.355F, 52.005F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-85.8504F, -60.4717F, 90.3553F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-102.1107F, -29.397F, 102.3709F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-113.3936F, -27.5113F, 118.085F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-159.5618F, -3.7147F, 163.529F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-172.1504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(22.3F, 23.7F, 35.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(20.955F, 13.63F, 47.645F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(18.22F, 6.095F, 49.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(10.67F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(-5.505F, 0.43F, 52.83F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-93.8583F, -74.9675F, 93.7266F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-86.6424F, -52.4887F, 91.303F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-105.8789F, -20.9109F, 112.5468F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-166.9536F, 7.9768F, 181.4866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-132.8382F, 37.2902F, 230.8687F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(13.8F, 20.0F, 42.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(10.58F, 7.13F, 49.67F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(1.52F, 1.27F, 52.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(-5.93F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(-22.005F, 1.13F, 52.03F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void dash_intro_3(AnimationDefinition.Builder b){b.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(87.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(104.3033F, 32.2362F, -246.8753F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-6.9F, 1.8F, -54.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(4.4F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(19.3F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(26.4F, 7.0F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(30.4F, 19.9F, -42.6F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("bone23", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(86.6072F, -45.1048F, -100.1054F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(93.439F, -29.9049F, -107.813F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(93.5006F, -41.8759F, -111.9761F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(177.5572F, -5.9959F, -197.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(102.3852F, 30.2628F, -239.017F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-30.81F, 14.6F, -46.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(-29.0F, 7.5F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(-16.6F, -0.1F, -52.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(-5.1F, 0.4F, -52.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(9.2F, 5.1F, -51.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("bone24", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void dash_intro_4(AnimationDefinition.Builder b){b.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(181.0207F, -21.4331F, -170.8872F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(161.0027F, 14.8667F, -204.743F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-18.51F, 7.3F, -51.07F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(-13.6F, -0.1F, -50.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(1.7F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(11.5F, 0.4F, -53.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(20.4F, 12.3F, -48.1F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("rhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(42.4164F, 14.8319F, 14.9156F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.4583F, KeyframeAnimations.degreeVec(30.883F, 23.8468F, -16.4034F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(2.8974F, 17.0474F, -30.8579F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("rhand", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.4583F, KeyframeAnimations.posVec(3.3F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("hand2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.4583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 17.5F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
public static final AnimationDefinition dash_loop=build_dash_loop();
private static AnimationDefinition build_dash_loop(){var b=AnimationDefinition.Builder.withLength(1.0F).looping();dash_loop_0(b);dash_loop_1(b);dash_loop_2(b);dash_loop_3(b);dash_loop_4(b);dash_loop_5(b);dash_loop_6(b);return b.build();}
private static void dash_loop_0(AnimationDefinition.Builder b){b.addAnimation("main", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("main", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 3.3F, 19.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.posVec(0.0F, 3.8F, 19.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 3.3F, 19.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("lhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(33.8364F, -18.458F, 10.8352F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void dash_loop_1(AnimationDefinition.Builder b){b.addAnimation("toe", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(77.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(70.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void dash_loop_2(AnimationDefinition.Builder b){b.addAnimation("toe4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, -10.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 15.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("bone4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, -12.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 15.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("bone5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, -15.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 12.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("mild", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("angry", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void dash_loop_3(AnimationDefinition.Builder b){b.addAnimation("shock", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-179.6504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(10.795F, 0.355F, 52.005F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-172.1504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-5.505F, 0.43F, 52.83F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-132.8382F, 37.2902F, 230.8687F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void dash_loop_4(AnimationDefinition.Builder b){b.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-22.005F, 1.13F, 52.03F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(30.4F, 19.9F, -42.6F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(102.3852F, 30.2628F, -239.017F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(9.2F, 5.1F, -51.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void dash_loop_5(AnimationDefinition.Builder b){b.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(20.4F, 12.3F, -48.1F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("rhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(34.9235F, 13.7212F, -15.5487F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(65.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void dash_loop_6(AnimationDefinition.Builder b){b.addAnimation("finger7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(70.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(65.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
public static final AnimationDefinition dash_end=build_dash_end();
private static AnimationDefinition build_dash_end(){var b=AnimationDefinition.Builder.withLength(1.4583F);dash_end_0(b);dash_end_1(b);dash_end_2(b);dash_end_3(b);dash_end_4(b);dash_end_5(b);dash_end_6(b);dash_end_7(b);return b.build();}
private static void dash_end_0(AnimationDefinition.Builder b){b.addAnimation("main", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-12.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("main", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 3.3F, 19.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(0.0F, -0.1F, 4.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, -0.1F, 4.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.posVec(0.0F, -0.1F, 1.6F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("wheel2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("wheel2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -3.2F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void dash_end_1(AnimationDefinition.Builder b){b.addAnimation("lhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(33.8364F, -18.458F, 10.8352F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(33.8135F, -11.2662F, -15.5736F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(21.6854F, -12.6693F, 3.8079F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("lhand", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(1.4583F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("hand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void dash_end_2(AnimationDefinition.Builder b){b.addAnimation("toe2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(77.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(70.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("finger4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toe4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("mild", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void dash_end_3(AnimationDefinition.Builder b){b.addAnimation("angry", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("shock", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-65.368F, -85.0914F, 69.4151F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-98.8842F, -43.1389F, 95.4885F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-99.7736F, -32.2351F, 102.8669F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-96.182F, -21.3222F, 113.3777F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-179.6504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-164.7451F, 19.0602F, 198.9403F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(-98.7921F, 40.3432F, 264.1216F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-154.6357F, 86.9786F, 200.4091F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.degreeVec(-282.6345F, 27.6039F, 79.6914F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.degreeVec(-350.8496F, -1.8962F, -4.2262F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.degreeVec(-272.8958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.degreeVec(-269.2719F, -29.1139F, -79.9636F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(-283.8457F, -48.0609F, -82.8255F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(31.1F, 25.9F, 28.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(30.655F, 18.93F, 42.345F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(29.32F, 13.095F, 47.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(27.295F, 6.075F, 50.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(10.795F, 0.355F, 52.005F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-3.13F, 0.44F, 51.63F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.posVec(-12.9F, 17.605F, 43.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-13.43F, 25.82F, 26.41F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.posVec(-12.23F, 13.945F, 5.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.posVec(16.125F, -0.14F, -0.465F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.posVec(27.92F, 8.025F, 1.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.posVec(30.4F, 14.09F, 5.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.posVec(30.065F, 18.675F, 7.84F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-85.8504F, -60.4717F, 90.3553F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-102.1107F, -29.397F, 102.3709F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-113.3936F, -27.5113F, 118.085F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-159.5618F, -3.7147F, 163.529F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-172.1504F, 2.4224F, 185.2635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-99.5273F, 20.7275F, 241.2514F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(-95.5207F, 59.9378F, 269.6097F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-263.2361F, 66.1476F, 91.2049F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.degreeVec(-285.094F, 22.0422F, 73.2632F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.degreeVec(-289.3974F, -33.3149F, -49.3241F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.degreeVec(-280.3958F, -32.0474F, -73.9874F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.degreeVec(-267.9874F, -48.5394F, -91.4801F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(-288.6993F, -60.0746F, -76.866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(22.3F, 23.7F, 35.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(20.955F, 13.63F, 47.645F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(18.22F, 6.095F, 49.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(10.67F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(-5.505F, 0.43F, 52.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-18.18F, 5.62F, 51.18F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.posVec(-22.0F, 24.005F, 36.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-22.23F, 25.02F, 18.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.posVec(-18.03F, 5.345F, 1.06F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.posVec(16.125F, 1.61F, -0.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.posVec(21.295F, 14.425F, 5.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.posVec(22.1F, 20.64F, 10.86F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.posVec(22.065F, 23.475F, 14.24F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void dash_end_4(AnimationDefinition.Builder b){b.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-93.8583F, -74.9675F, 93.7266F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-86.6424F, -52.4887F, 91.303F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-105.8789F, -20.9109F, 112.5468F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-166.9536F, 7.9768F, 181.4866F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(-132.8382F, 37.2902F, 230.8687F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-95.6712F, 16.3924F, 246.8258F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(-181.1006F, 87.2371F, 183.2895F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-265.0888F, 56.1979F, 89.0981F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.degreeVec(-343.6514F, 15.7568F, 12.7989F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-363.3472F, -2.5577F, -0.994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.degreeVec(-279.8191F, -36.8748F, -65.8983F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.degreeVec(-266.4444F, -45.0479F, -93.4194F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.degreeVec(-263.3136F, -78.4806F, -96.5246F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(-259.6189F, -80.5013F, -98.0023F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(-398.3715F, -78.349F, 37.0455F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-93.8583F, -74.9675F, 93.7266F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(13.8F, 20.0F, 42.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(10.58F, 7.13F, 49.67F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(1.52F, 1.27F, 52.85F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(-5.93F, 1.15F, 53.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(-22.005F, 1.13F, 52.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(-29.61F, 12.62F, 47.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.posVec(-30.5F, 26.105F, 26.01F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(-30.63F, 20.82F, 11.51F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.posVec(-16.68F, 0.045F, -1.09F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.posVec(-1.595F, -0.12F, 0.205F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.posVec(11.225F, 9.11F, 1.81F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.posVec(13.095F, 20.825F, 10.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.posVec(13.4F, 24.74F, 18.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(87.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(104.3033F, 32.2362F, -246.8753F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(255.5873F, 81.4735F, -95.7486F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(267.3615F, 36.7089F, -83.0666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(362.4224F, 6.9398F, -0.8472F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.degreeVec(284.0933F, -81.2714F, 81.9731F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(445.2676F, -63.3915F, -79.8573F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(447.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(87.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-6.9F, 1.8F, -54.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(4.4F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(19.3F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(26.4F, 7.0F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(30.4F, 19.9F, -42.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.posVec(30.8F, 26.4F, -26.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(30.8F, 18.7F, -9.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.posVec(26.1F, 4.2F, -1.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.posVec(-11.7F, 11.7F, -3.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.posVec(-13.1F, 25.3F, -20.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.posVec(-13.6F, 22.9F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(86.6072F, -45.1048F, -100.1054F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(93.439F, -29.9049F, -107.813F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(93.5006F, -41.8759F, -111.9761F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(177.5572F, -5.9959F, -197.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(102.3852F, 30.2628F, -239.017F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(95.8788F, 39.0284F, -264.8805F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(97.3809F, 88.5476F, -264.539F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.degreeVec(273.0528F, 42.9326F, -81.7794F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(280.8679F, 37.7136F, -69.9546F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.degreeVec(355.4252F, -2.9184F, 6.5199F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.degreeVec(273.719F, -19.6137F, 80.6991F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(268.4388F, -61.5322F, 98.1823F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-30.81F, 14.6F, -46.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(-29.0F, 7.5F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(-16.6F, -0.1F, -52.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(-5.1F, 0.4F, -52.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(9.2F, 5.1F, -51.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.posVec(12.7F, 16.9F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(13.4F, 26.5F, -24.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.posVec(13.25F, 17.4F, -9.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.posVec(10.06F, 6.69F, -3.16F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.posVec(-18.8F, -0.1F, -0.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.posVec(-29.95F, 13.9F, -5.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.posVec(-30.2F, 24.9F, -19.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void dash_end_5(AnimationDefinition.Builder b){b.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(109.7317F, -41.8257F, -137.3666F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(107.3589F, -35.9361F, -113.4785F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(171.7235F, -18.4072F, -155.8771F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(181.0207F, -21.4331F, -170.8872F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(180.9531F, 4.5632F, -170.4384F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(161.0027F, 14.8667F, -204.743F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(98.5315F, 34.0236F, -257.4138F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(103.895F, 70.6504F, -255.4522F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(265.5404F, 61.6655F, -85.4158F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.degreeVec(278.4742F, 18.8342F, -65.0482F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(308.2499F, 31.2143F, -27.8326F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.degreeVec(352.9907F, -2.0597F, 5.6714F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.degreeVec(281.7516F, -21.2122F, 70.7508F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.degreeVec(273.0766F, -46.489F, 93.6773F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(442.5951F, -73.3384F, -76.9932F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.posVec(-18.51F, 7.3F, -51.07F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.posVec(-13.6F, -0.1F, -50.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.posVec(1.7F, -0.1F, -52.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(11.5F, 0.4F, -53.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.posVec(20.4F, 12.3F, -48.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.posVec(22.0F, 22.6F, -37.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.posVec(22.2F, 23.8F, -15.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.posVec(20.4F, 11.1F, -3.6F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.posVec(13.46F, 0.99F, 0.44F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.posVec(-0.1F, 0.3F, -0.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.125F, KeyframeAnimations.posVec(-17.2F, 4.2F, -1.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.posVec(-21.7F, 20.5F, -11.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.posVec(-21.9F, 26.6F, -28.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("rhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(34.9235F, 13.7212F, -15.5487F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(17.5233F, 6.3287F, 28.7688F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(14.3433F, 4.5575F, 3.6318F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("rhand", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(1.4583F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("hand2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("hand2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void dash_end_6(AnimationDefinition.Builder b){b.addAnimation("finger5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(65.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("finger6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("finger7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(70.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void dash_end_7(AnimationDefinition.Builder b){b.addAnimation("finger8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(65.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
public static final AnimationDefinition dead=build_dead();
private static AnimationDefinition build_dead(){var b=AnimationDefinition.Builder.withLength(2.2917F);dead_0(b);dead_1(b);dead_2(b);dead_3(b);dead_4(b);dead_5(b);return b.build();}
private static void dead_0(AnimationDefinition.Builder b){b.addAnimation("main", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-35.1866F, 360.7606F, -2.1856F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.degreeVec(-88.6866F, 360.7606F, -2.1856F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(-142.1866F, 360.7606F, -2.1856F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(-142.1866F, 360.7606F, -2.1856F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.2083F, KeyframeAnimations.degreeVec(-142.1866F, 360.7606F, -2.1856F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("main", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.posVec(0.0F, 1.35F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.posVec(0.0F, 36.89F, -18.1F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.posVec(0.0F, 36.1F, -18.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.posVec(0.0F, 32.8F, -18.7F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.2083F, KeyframeAnimations.posVec(0.0F, 36.1F, -18.7F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("wheel2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-2.2209F, -0.2923F, -7.4943F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5833F, KeyframeAnimations.degreeVec(4.1136F, -0.8841F, 4.1014F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("lhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-69.3976F, -6.5139F, 21.6305F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.degreeVec(-69.3976F, -6.5139F, 21.6305F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("hand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(132.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("hand", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(1.3333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.posVec(10.6F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.posVec(28.1F, 9.7F, -14.1F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void dead_1(AnimationDefinition.Builder b){b.addAnimation("finger1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(75.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("finger2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(42.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("finger3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void dead_2(AnimationDefinition.Builder b){b.addAnimation("finger4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("mild", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("angry", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("shock", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("close", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void dead_3(AnimationDefinition.Builder b){b.addAnimation("rhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-48.4733F, -1.2631F, -24.4886F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("arm2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("arm2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(1.8333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("hand2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.degreeVec(120.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("hand2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(1.3333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.125F, KeyframeAnimations.posVec(-29.1F, 16.8F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("finger5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void dead_4(AnimationDefinition.Builder b){b.addAnimation("toe5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(87.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("finger6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(77.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("finger7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toe7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(82.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("finger8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void dead_5(AnimationDefinition.Builder b){b.addAnimation("toe8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(82.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
public static final AnimationDefinition unconscious=build_unconscious();
private static AnimationDefinition build_unconscious(){var b=AnimationDefinition.Builder.withLength(4.0F);unconscious_0(b);unconscious_1(b);unconscious_2(b);unconscious_3(b);unconscious_4(b);return b.build();}
private static void unconscious_0(AnimationDefinition.Builder b){b.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7917F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.2083F, KeyframeAnimations.degreeVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(0.9974F, 0.3173F, -0.9483F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(0.9993F, -0.1587F, 0.4742F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(0.9974F, 0.3173F, -0.9483F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.875F, KeyframeAnimations.degreeVec(0.9993F, -0.1587F, 0.4742F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7917F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.2083F, KeyframeAnimations.degreeVec(-0.7612F, -0.459F, -1.9466F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(-0.7732F, 0.5285F, 2.2371F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(-0.7612F, -0.459F, -1.9466F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(-0.7732F, 0.5285F, 2.2371F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("yanpi", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.3F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("yanpi2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("mild", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("angry", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void unconscious_1(AnimationDefinition.Builder b){b.addAnimation("shock", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("close", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("lhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7917F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.2083F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(-2.6805F, 0.3843F, 0.9235F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(-2.4694F, -0.3813F, -0.925F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(-2.6805F, 0.3843F, 0.9235F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.875F, KeyframeAnimations.degreeVec(-2.4694F, -0.3813F, -0.925F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("lhand", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7917F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.2083F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("rhand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7917F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.2083F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(-2.4694F, 0.3813F, 0.925F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(-2.5245F, -0.3818F, -0.9246F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(-2.4694F, 0.3813F, 0.925F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.875F, KeyframeAnimations.degreeVec(-2.5245F, -0.3818F, -0.9246F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("rhand", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7917F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.2083F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void unconscious_2(AnimationDefinition.Builder b){b.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(90.0F, -69.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain1", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(30.6F, 24.1F, 15.3F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(22.3F, 25.6F, 23.9F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-93.8583F, -74.9675F, 93.7266F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain3", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(13.6F, 25.5F, 32.7F), AnimationChannel.Interpolations.CATMULLROM)
		))
		;}
private static void unconscious_3(AnimationDefinition.Builder b){b.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(87.634F, -45.4105F, -105.7724F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain4", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-11.9F, 9.8F, -50.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("bone23", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(92.8832F, -50.3777F, -93.5031F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain5", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-30.8F, 21.9F, -39.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("bone24", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
private static void unconscious_4(AnimationDefinition.Builder b){b.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(94.1861F, -34.7805F, -97.3116F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("toytrain6", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-21.7F, 16.2F, -45.4F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("bone25", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)
		))
		;}
public static final java.util.Map<String,AnimationDefinition> ALL=java.util.Map.ofEntries(java.util.Map.entry("idle",idle),java.util.Map.entry("attack",attack),java.util.Map.entry("move",move),java.util.Map.entry("skill1",skill1),java.util.Map.entry("skill2",skill2),java.util.Map.entry("dash_intro",dash_intro),java.util.Map.entry("dash_loop",dash_loop),java.util.Map.entry("dash_end",dash_end),java.util.Map.entry("dead",dead),java.util.Map.entry("unconscious",unconscious));
}