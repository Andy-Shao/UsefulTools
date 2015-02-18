package com.github.andyshao.data.structure;

import java.util.Objects;

public interface CycleLinkedElmt<DATA> extends Linked.LinkedElmt<DATA , CycleLinkedElmt<DATA>> {
    public static <DAT> CycleLinkedElmt<DAT> DEFAULT_ELMT(DAT data) {
        CycleLinkedElmt<DAT> result = new CycleLinkedElmt<DAT>() {
            private DAT data;
            private CycleLinkedElmt<DAT> next;

            @SuppressWarnings("unchecked")
            @Override
            public boolean equals(Object obj) {
                CycleLinkedElmt<DAT> that;
                if (obj instanceof CycleLinkedElmt) {
                    that = (CycleLinkedElmt<DAT>) obj;
                    if (this.next == this) return Objects.equals(this.data , that.list_Data());
                    else return Objects.equals(this.data , that.list_Data())
                        && Objects.equals(this.next , that.list_next());
                } else return false;
            }

            @Override
            public int hashCode() {
                if (this.next == this) return this.data.hashCode();
                else return Objects.hash(this.data , this.next);
            }

            @Override
            public DAT list_Data() {
                return this.data;
            }

            @Override
            public CycleLinkedElmt<DAT> list_next() {
                return this.next;
            }

            @Override
            public void setData(DAT data) {
                this.data = data;
            }

            @Override
            public void setNext(CycleLinkedElmt<DAT> next) {
                this.next = next;
            }
        };
        result.setData(data);

        return result;
    }
}