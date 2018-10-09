package com.yusj.rhinoceros.server.quorum;

public class QuorumStats {
    private final Provider provider;

    /**
     *
     */
    public interface Provider {
        static public final String UNKNOWN_STATE = "unknown";
        /**
         * 寻找Leader状态。当服务器处于该状态时，它会认为当前集群中没有Leader，因此需要进入Leader选举状态。
         */
        static public final String LOOKING_STATE = "leaderelection";
        /**
         * 领导者状态。表明当前服务器角色是Leader。
         */
        static public final String LEADING_STATE = "leading";
        /**
         * 跟随者状态。表明当前服务器角色是Follower。
         */
        static public final String FOLLOWING_STATE = "following";
        /**
         * 观察者状态。表明当前服务器角色是Observer。
         */
        static public final String OBSERVING_STATE = "observing";

        public String[] getQuorumPeers();

        public String getServerState();
    }

    protected QuorumStats(Provider provider) {
        this.provider = provider;
    }

    public String getServerState() {
        return provider.getServerState();
    }

    public String[] getQuorumPeers() {
        return provider.getQuorumPeers();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        String state = getServerState();
        if (state.equals(Provider.LEADING_STATE)) {
            sb.append("Followers:");
            for (String f : getQuorumPeers()) {
                sb.append(" ").append(f);
            }
            sb.append("\n");
        } else if (state.equals(Provider.FOLLOWING_STATE)
                || state.equals(Provider.OBSERVING_STATE)) {
            sb.append("Leader: ");
            String[] ldr = getQuorumPeers();
            if (ldr.length > 0)
                sb.append(ldr[0]);
            else
                sb.append("not connected");
            sb.append("\n");
        }
        return sb.toString();
    }
}
