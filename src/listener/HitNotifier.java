// ID: 337914121
package listener;

/**
 * The HitNotifier interface.
 * indicate that objects that implement it send notifications when they are being hit
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl a hitlistener
     */
    void addHitListener(HitListener hl);

    /**
     * remove hl from the list of listeners to hit events.
     * @param hl a hitlistener
     */
    void removeHitListener(HitListener hl);
}
