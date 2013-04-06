 /**
 *
 * @author hp
 * @param <FST>
 * @param <SND>
 */
public class Pair<FST, SND> {         
    /**
     *
     */
    public final FST fst;
    /**
     *
     */
    public final SND snd;

    /**
     *
     * @param fst
     * @param snd
     */
    public Pair(FST fst, SND snd) {         
        this.fst= fst;
        this.snd= snd;
     }
 }