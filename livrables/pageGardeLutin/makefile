all : compilRapport clean
compilRapport : page.pdf

page.pdf : page.tex
	pdflatex page.tex && pdflatex page.tex 
clean : 
	rm page.aux page.log page.maf page.mtc* page.out page.toc page.tex.* page.pyg