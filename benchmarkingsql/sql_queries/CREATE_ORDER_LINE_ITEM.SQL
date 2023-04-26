-- Table: public.order_line_item

-- DROP TABLE IF EXISTS public.order_line_item;

CREATE TABLE IF NOT EXISTS public.order_line_item
(
    id bigint NOT NULL,
    order_id bigint NOT NULL,
    product_id bigint NOT NULL,
    quantity integer NOT NULL,
    price double precision NOT NULL,
    CONSTRAINT order_line_item_pkey PRIMARY KEY (id),
    CONSTRAINT order_id FOREIGN KEY (order_id)
        REFERENCES public."order" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT product_id FOREIGN KEY (product_id)
        REFERENCES public.product (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.order_line_item
    OWNER to admin;