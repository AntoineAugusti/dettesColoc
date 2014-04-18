all : compilRapport clean
compilRapport : cdc.pdf

cdc.pdf : cdc.tex
	pdflatex cdc.tex && pdflatex cdc.tex 
clean : 
	rm cdc.aux cdc.log cdc.maf cdc.mtc* cdc.out cdc.toc cdc.tex.* cdc.pyg