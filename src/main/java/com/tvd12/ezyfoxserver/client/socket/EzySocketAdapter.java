package com.tvd12.ezyfoxserver.client.socket;

public abstract class EzySocketAdapter {
    protected volatile boolean active;
    protected volatile boolean stopped;
    protected final Object adapterLock;

    public EzySocketAdapter () {
        this.active = false;
        this.stopped = false;
        this.adapterLock = new Object();
    }

    public void start () {
        synchronized (adapterLock) {
            if (active) {
                return;
            }
            active = true;
            stopped = false;
            Thread newThread = new Thread(new Runnable() {
                @Override
                public void run () {
                    loop();
                }
            });
            newThread.setName(getThreadName());
            newThread.start();
        }
    }

    protected abstract String getThreadName ();

    protected void loop () {
        update();
        setStopped();
    }

    protected abstract void update ();

    public void stop () {
        synchronized (adapterLock) {
            active = false;
        }
    }

    protected void setActive (boolean active) {
        synchronized (adapterLock) {
            this.active = active;
        }
    }

    protected void setStopped () {
        synchronized (adapterLock) {
            this.stopped = true;
        }
    }

    public boolean isActive () {
        synchronized (adapterLock) {
            return active;
        }
    }

    public boolean isStopped () {
        synchronized (adapterLock) {
            return stopped;
        }
    }
}
