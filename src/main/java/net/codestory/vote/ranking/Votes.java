package net.codestory.vote.ranking;

import java.util.*;

public class Votes {
  private static float[] pdH = {.5f, .5f, .5f, .5f, .51f, .51f, .51f, .51f, .51f, .51f, .51f, .52f, .52f, .52f, .52f, .52f, .52f, .52f, .53f, .53f, .53f, .53f, .53f, .53f, .53f, .53f, .54f, .54f, .54f, .54f, .54f, .54f, .54f, .55f, .55f, .55f, .55f, .55f, .55f, .55f, .56f, .56f, .56f, .56f, .56f, .56f, .56f, .57f, .57f, .57f, .57f, .57f, .57f, .57f, .58f, .58f, .58f, .58f, .58f, .58f, .58f, .58f, .59f, .59f, .59f, .59f, .59f, .59f, .59f, .6f, .6f, .6f, .6f, .6f, .6f, .6f, .6f, .61f, .61f, .61f, .61f, .61f, .61f, .61f, .62f, .62f, .62f, .62f, .62f, .62f, .62f, .62f, .63f, .63f, .63f, .63f, .63f, .63f, .63f, .64f, .64f, .64f, .64f, .64f, .64f, .64f, .64f, .65f, .65f, .65f, .65f, .65f, .65f, .65f, .66f, .66f, .66f, .66f, .66f, .66f, .66f, .66f, .67f, .67f, .67f, .67f, .67f, .67f, .67f, .67f, .68f, .68f, .68f, .68f, .68f, .68f, .68f, .68f, .69f, .69f, .69f, .69f, .69f, .69f, .69f, .69f, .7f, .7f, .7f, .7f, .7f, .7f, .7f, .7f, .71f, .71f, .71f, .71f, .71f, .71f, .71f, .71f, .71f, .72f, .72f, .72f, .72f, .72f, .72f, .72f, .72f, .73f, .73f, .73f, .73f, .73f, .73f, .73f, .73f, .73f, .74f, .74f, .74f, .74f, .74f, .74f, .74f, .74f, .74f, .75f, .75f, .75f, .75f, .75f, .75f, .75f, .75f, .75f, .76f, .76f, .76f, .76f, .76f, .76f, .76f, .76f, .76f, .77f, .77f, .77f, .77f, .77f, .77f, .77f, .77f, .77f, .78f, .78f, .78f, .78f, .78f, .78f, .78f, .78f, .78f, .78f, .79f, .79f, .79f, .79f, .79f, .79f, .79f, .79f, .79f, .79f, .8f, .8f, .8f, .8f, .8f, .8f, .8f, .8f, .8f, .8f, .81f, .81f, .81f, .81f, .81f, .81f, .81f, .81f, .81f, .81f, .81f, .82f, .82f, .82f, .82f, .82f, .82f, .82f, .82f, .82f, .82f, .82f, .83f, .83f, .83f, .83f, .83f, .83f, .83f, .83f, .83f, .83f, .83f, .84f, .84f, .84f, .84f, .84f, .84f, .84f, .84f, .84f, .84f, .84f, .84f, .85f, .85f, .85f, .85f, .85f, .85f, .85f, .85f, .85f, .85f, .85f, .85f, .86f, .86f, .86f, .86f, .86f, .86f, .86f, .86f, .86f, .86f, .86f, .86f, .86f, .87f, .87f, .87f, .87f, .87f, .87f, .87f, .87f, .87f, .87f, .87f, .87f, .87f, .88f, .88f, .88f, .88f, .88f, .88f, .88f, .88f, .88f, .88f, .88f, .88f, .88f, .88f, .88f, .88f, .89f, .89f, .89f, .89f, .89f, .89f, .89f, .89f, .89f, .89f, .89f, .89f, .89f, .9f, .9f, .9f, .9f, .9f, .9f, .9f, .9f, .9f, .9f, .9f, .9f, .9f, .9f, .9f, .9f, .9f, .91f, .91f, .91f, .91f, .91f, .91f, .91f, .91f, .91f, .91f, .91f, .91f, .91f, .91f, .91f, .91f, .91f, .92f, .92f, .92f, .92f, .92f, .92f, .92f, .92f, .92f};
  private static float[] pdL = {.5f, .5f, .5f, .5f, .49f, .49f, .49f, .49f, .49f, .49f, .49f, .48f, .48f, .48f, .48f, .48f, .48f, .48f, .47f, .47f, .47f, .47f, .47f, .47f, .47f, .47f, .46f, .46f, .46f, .46f, .46f, .46f, .46f, .45f, .45f, .45f, .45f, .45f, .45f, .45f, .44f, .44f, .44f, .44f, .44f, .44f, .44f, .43f, .43f, .43f, .43f, .43f, .43f, .43f, .42f, .42f, .42f, .42f, .42f, .42f, .42f, .42f, .41f, .41f, .41f, .41f, .41f, .41f, .41f, .4f, .4f, .4f, .4f, .4f, .4f, .4f, .4f, .39f, .39f, .39f, .39f, .39f, .39f, .39f, .38f, .38f, .38f, .38f, .38f, .38f, .38f, .38f, .37f, .37f, .37f, .37f, .37f, .37f, .37f, .36f, .36f, .36f, .36f, .36f, .36f, .36f, .36f, .35f, .35f, .35f, .35f, .35f, .35f, .35f, .34f, .34f, .34f, .34f, .34f, .34f, .34f, .34f, .33f, .33f, .33f, .33f, .33f, .33f, .33f, .33f, .32f, .32f, .32f, .32f, .32f, .32f, .32f, .32f, .31f, .31f, .31f, .31f, .31f, .31f, .31f, .31f, .3f, .3f, .3f, .3f, .3f, .3f, .3f, .3f, .29f, .29f, .29f, .29f, .29f, .29f, .29f, .29f, .29f, .28f, .28f, .28f, .28f, .28f, .28f, .28f, .28f, .27f, .27f, .27f, .27f, .27f, .27f, .27f, .27f, .27f, .26f, .26f, .26f, .26f, .26f, .26f, .26f, .26f, .26f, .25f, .25f, .25f, .25f, .25f, .25f, .25f, .25f, .25f, .24f, .24f, .24f, .24f, .24f, .24f, .24f, .24f, .24f, .23f, .23f, .23f, .23f, .23f, .23f, .23f, .23f, .23f, .22f, .22f, .22f, .22f, .22f, .22f, .22f, .22f, .22f, .22f, .21f, .21f, .21f, .21f, .21f, .21f, .21f, .21f, .21f, .21f, .2f, .2f, .2f, .2f, .2f, .2f, .2f, .2f, .2f, .2f, .19f, .19f, .19f, .19f, .19f, .19f, .19f, .19f, .19f, .19f, .19f, .18f, .18f, .18f, .18f, .18f, .18f, .18f, .18f, .18f, .18f, .18f, .17f, .17f, .17f, .17f, .17f, .17f, .17f, .17f, .17f, .17f, .17f, .16f, .16f, .16f, .16f, .16f, .16f, .16f, .16f, .16f, .16f, .16f, .16f, .15f, .15f, .15f, .15f, .15f, .15f, .15f, .15f, .15f, .15f, .15f, .15f, .14f, .14f, .14f, .14f, .14f, .14f, .14f, .14f, .14f, .14f, .14f, .14f, .14f, .13f, .13f, .13f, .13f, .13f, .13f, .13f, .13f, .13f, .13f, .13f, .13f, .13f, .12f, .12f, .12f, .12f, .12f, .12f, .12f, .12f, .12f, .12f, .12f, .12f, .12f, .12f, .12f, .12f, .11f, .11f, .11f, .11f, .11f, .11f, .11f, .11f, .11f, .11f, .11f, .11f, .11f, .1f, .1f, .1f, .1f, .1f, .1f, .1f, .1f, .1f, .1f, .1f, .1f, .1f, .1f, .1f, .1f, .1f, .09f, .09f, .09f, .09f, .09f, .09f, .09f, .09f, .09f, .09f, .09f, .09f, .09f, .09f, .09f, .09f, .09f, .08f, .08f, .08f, .08f, .08f, .08f, .08f, .08f, .08f};

  public static final int MAX = 10;
  public static final float START_SCORE = 1200f;

  private final int[] played;
  private final float[] ranking;

  public Votes() {
    played = new int[MAX];
    ranking = new float[MAX];
    Arrays.fill(ranking, START_SCORE);
  }

  public synchronized int score(int index) {
    return (int) ranking[index];
  }

  public synchronized void match(int winner, int looser) {
    int playedW = ++played[winner];
    int playedL = ++played[looser];

    float scoreW = ranking[winner];
    float scoreL = ranking[looser];

    if (Math.abs(scoreW - scoreL) > 400) {
      return;
    }

    ranking[winner] += k(scoreW, playedW) * (1 - pd(scoreW, scoreL));
    ranking[looser] += k(scoreL, playedL) * (0 - pd(scoreL, scoreW));
  }

  static float pd(double score1, double score2) {
    return score1 > score2 ? pdH[(int) (score1 - score2)] : pdL[(int) (score2 - score1)];
  }

  static int k(float score, int played) {
    return played < 30 ? 25 : score <= 2400 ? 15 : 10;
  }
}
