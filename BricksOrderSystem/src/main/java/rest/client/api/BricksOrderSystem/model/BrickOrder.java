package rest.client.api.BricksOrderSystem.model;

public class BrickOrder {
    private long id;
    private long noOfBricks;
    private boolean isShipped=false; 

    public BrickOrder() {
    }

    public BrickOrder(long id, long noOfBricks) {
        this.id = id;
        this.noOfBricks = noOfBricks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNoOfBricks() {
        return noOfBricks;
    }

    public void setNoOfBricks(long noOfBricks) {
        this.noOfBricks = noOfBricks;
    }

	public boolean isShipped() {
		return isShipped;
	}

	public void setShipped(boolean isShipped) {
		this.isShipped = isShipped;
	}

    
}
