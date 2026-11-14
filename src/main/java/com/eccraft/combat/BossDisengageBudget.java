package com.eccraft.combat;
/** Spent tick budget: 0 means full. Only call failedAttempt when the AI actually reaches normal attack selection. */
public final class BossDisengageBudget {
 public static int failedAttempt(int spent,int maximum){return (int)Math.min(Math.max(0,maximum),(long)Math.max(0,spent)+1);}
 public static int attackStarted(int spent,int maximum){return Math.max(0,Math.min(Math.max(0,maximum),spent)-20);}
 public static int restore(int saved,int maximum){return Math.clamp(saved,0,Math.max(0,maximum));}
 private BossDisengageBudget(){}
}