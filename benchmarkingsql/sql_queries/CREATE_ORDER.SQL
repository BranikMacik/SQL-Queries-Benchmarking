-- Table: public.order

-- DROP TABLE IF EXISTS public."order";

CREATE TABLE IF NOT EXISTS public."order"
(
    id bigint NOT NULL,
    customer_id bigint NOT NULL,
    order_date date NOT NULL,
    total_amount double precision NOT NULL,
    CONSTRAINT order_pkey PRIMARY KEY (id),
    CONSTRAINT customer_id FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."order"
    OWNER to admin;